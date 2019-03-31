package com.reward.api.service.currency;

import java.math.BigDecimal;

public interface ExchangeRateService
{
   void getExchangeRates();

   BigDecimal getExchangeRateOf(String currency);
}
