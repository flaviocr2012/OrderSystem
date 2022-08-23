package com.tests.integration.Tests.controller;

import com.tests.integration.Tests.model.Order;
import com.tests.integration.Tests.model.OrderRequest;
import com.tests.integration.Tests.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder (
            OrderRequest orderRequest,
            UriComponentsBuilder uriComponentsBuilder) {

        Order order = orderService.createOrder(orderRequest.getAmount());
        URI location = uriComponentsBuilder.path("/order/path").buildAndExpand(order.getId()).toUri();
        return ResponseEntity.created(location).body(order);
    }


}
