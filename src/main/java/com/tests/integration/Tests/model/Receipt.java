package com.tests.integration.Tests.model;

import lombok.Data;

import java.time.LocalDateTime;
import javax.money.MonetaryAmount;

@Data
public class Receipt {

    private final LocalDateTime date;
    private final String creditCardNumber;
    private final MonetaryAmount amount;

}
