package com.tests.integration.Tests.service_test;

import com.tests.integration.Tests.entity.Order;
import com.tests.integration.Tests.entity.Payment;
import com.tests.integration.Tests.exception.PaymentException;
import com.tests.integration.Tests.repository.OrderRepository;
import com.tests.integration.Tests.repository.PaymentRepository;
import com.tests.integration.Tests.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldPaidOrderSucessfully() {

        Order order = new Order(1L, false);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(paymentRepository.save(any())).thenAnswer(returnsFirstArg());

        Payment payment = orderService.pay(1L, "4532756279624064");

        assertThat(payment.getOrder().isPaid()).isTrue();
        assertThat(payment.getCreditCardNumber()).isEqualTo("4532756279624064");

    }

    @Test
    void shouldNotPayAlreadyPayOrder() {
        Order order = new Order(1L,  true);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        assertThrows(PaymentException.class,
                () -> orderService.pay(order.getId(), "4556622577268643"));

    }

}