package com.reward.api.service.external;

import org.springframework.http.ResponseEntity;

public interface OpenExchangeApiService {
    ResponseEntity<String> getExchangeRateApiResponse();
}
