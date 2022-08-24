package com.tests.integration.Tests.service;

import com.tests.integration.Tests.component.ExchangeRateClient;
import com.tests.integration.Tests.entity.Order;
import com.tests.integration.Tests.entity.Payment;
import com.tests.integration.Tests.exception.OrderAlreadyPaid;
import com.tests.integration.Tests.repository.OrderRepository;
import com.tests.integration.Tests.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.money.MonetaryAmount;
import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    private final ExchangeRateClient exchangeRateClient;

    public Order createOrder(MonetaryAmount amount) {

        return null;
    }

    public Order getOrder(Long orderId) {
        return null;
    }

    public Payment pay(Long orderId, String creditCardNumber) {
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);

        if(order.isPaid()) {
            throw new OrderAlreadyPaid();
        }

        orderRepository.save(order.markPaid());
        return  paymentRepository.save(new Payment(order, creditCardNumber));

    }
}
