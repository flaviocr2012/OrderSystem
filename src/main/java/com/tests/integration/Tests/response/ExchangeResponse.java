package com.tests.integration.Tests.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeResponse {
    @JsonAlias
    private BigDecimal conversionRate;
    private String result;
    @JsonAlias
    private String errorType;

}
