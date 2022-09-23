package com.tests.integration.Tests.service;

import com.tests.integration.Tests.component.ExchangeRateClient;
import com.tests.integration.Tests.entity.Order;
import com.tests.integration.Tests.entity.Payment;
import com.tests.integration.Tests.exception.OrderAlreadyPaid;
import com.tests.integration.Tests.exception.PaymentException;
import com.tests.integration.Tests.model.Receipt;
import com.tests.integration.Tests.repository.OrderRepository;
import com.tests.integration.Tests.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Service;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    private final ExchangeRateClient exchangeRateClient;

    public Order createOrder(MonetaryAmount amount) {
        Order order = new Order(LocalDateTime.now(), amount.getNumber().numberValue(BigDecimal.class), false);
        return orderRepository.save(order);
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
    }

    public Payment pay(Long orderId, String creditCardNumber) {
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);

        if (order.isPaid()) {
            throw new PaymentException();
        }

        orderRepository.save(order.markPaid());
        return paymentRepository.save(new Payment(order, creditCardNumber));
    }


    public Receipt getReceipt(Long orderId, CurrencyUnit currency) {
        Payment payment = paymentRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        BigDecimal rate = exchangeRateClient.getExchangeRate(Monetary.getCurrency("EUR"), currency);

        BigDecimal amount = payment.getOrder().getAmount();
        MonetaryAmount convertedAmount = Money.of(amount.multiply(rate), currency);

        return new Receipt(payment.getOrder().getDate(), payment.getCreditCardNumber(), convertedAmount);
    }
}
