package com.reward.api.service.currency;

import java.math.BigDecimal;

public interface ConverterService
{
   BigDecimal convert(BigDecimal value, String fromCurrency, String toCurrency);

   BigDecimal convertToUSD(BigDecimal value,  String currency);
}
