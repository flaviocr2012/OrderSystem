package com.tests.integration.Tests.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    @CreditCardNumber
    private String creditCardNumber;

}
