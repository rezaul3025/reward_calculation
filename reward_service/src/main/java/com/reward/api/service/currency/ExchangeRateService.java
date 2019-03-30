package com.reward.api.service.currency;

import java.math.BigDecimal;
import java.util.Map;

public interface ExchangeRateService
{
   void getExchangeRates();

   BigDecimal getExchangeRateOf(String currency);
}
