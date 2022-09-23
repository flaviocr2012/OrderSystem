package com.tests.integration.Tests.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "orders")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private LocalDateTime date;

    @NonNull
    private BigDecimal amount;
    @NonNull
    private Boolean paid;

    public Order(Long id, @NonNull Boolean paid) {
        this.id = id;
        this.paid = paid;
    }

    public boolean isPaid () {
        return paid;
    }

    public Order markPaid() {
        paid = true;
        return this;
    }

}
