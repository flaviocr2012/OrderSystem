package com.tests.integration.Tests.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "orders")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;

    private LocalDateTime date;

    private BigDecimal amount;
    private Boolean paid;

    public boolean isPaid () {
        return paid;
    }

    public Order markPaid() {
        paid = true;
        return this;
    }

}
