package com.tests.integration.Tests.component;

import com.tests.integration.Tests.config.ExchangeClientProperties;
import com.tests.integration.Tests.exception.ExchangeFailure;
import com.tests.integration.Tests.response.ExchangeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.springframework.web.reactive.function.client.WebClient;

import javax.money.CurrencyUnit;
import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ExchangeRateClient {

    private final WebClient webClient;
    private final ExchangeClientProperties properties;

    public BigDecimal getExchangeRate(CurrencyUnit from, CurrencyUnit to) {
        String baseUrl = properties.getBaseUrl();
        String apiKey = properties.getApiKey();

        return webClient.get()
                .uri(baseUrl + "/v6/{apiKey}/pair/{from}/{to}", apiKey, from, to)
                .retrieve()
                .bodyToMono(ExchangeResponse.class)
                .blockOptional()
                .map(response -> {
                    if ("error".equals(response.getResult())) {
                        throw new ExchangeFailure();
                    } else {
                        return response.getConversionRate();

                    }
                })
                .orElseThrow(ExchangeFailure::new);
    }

}
