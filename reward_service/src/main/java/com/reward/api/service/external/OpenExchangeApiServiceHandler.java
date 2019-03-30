package com.reward.api.service.external;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenExchangeApiServiceHandler implements OpenExchangeApiService {

    private final String API_ID = "cacd65f9d1964a559ba2aeff0c675342";

    private final String EXCHANGE_RATE_API_URL = String.format("https://openexchangerates.org/api/latest.json?app_id=%s", API_ID);

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<String> getExchangeRateApiResponse() {

        return restTemplate.getForEntity(EXCHANGE_RATE_API_URL, String.class);
    }
}
