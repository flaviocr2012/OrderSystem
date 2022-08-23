package com.tests.integration.Tests.request;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.money.MonetaryAmount;

@Data
@NoArgsConstructor
public class OrderRequest {

    @NotNull
    private MonetaryAmount amount;
}
