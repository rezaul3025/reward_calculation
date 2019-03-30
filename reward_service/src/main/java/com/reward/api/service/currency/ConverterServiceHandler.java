package com.reward.api.service.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class ConverterServiceHandler implements ConverterService
{
   private ExchangeRateService exchangeRateService;

   private final MathContext DEFAULT_CONTEXT = new MathContext(12, RoundingMode.HALF_DOWN);


   @Autowired
   public ConverterServiceHandler(ExchangeRateService exchangeRateService){
      this.exchangeRateService = exchangeRateService;
   }

   @Override
   public BigDecimal convert(@NotNull BigDecimal value, @NotNull String fromCurrency, @NotNull String toCurrency)
   {
      BigDecimal usdValue = convertToUSD(value, fromCurrency);

      BigDecimal rate = exchangeRateService.getExchangeRateOf(toCurrency);

      return  usdValue.multiply(rate, DEFAULT_CONTEXT);
   }

   @Override
   public BigDecimal convertToUSD(BigDecimal value, String currency)
   {
      BigDecimal rate = exchangeRateService.getExchangeRateOf(currency);

      BigDecimal currencyBasedRate =  BigDecimal.ONE.divide(rate, DEFAULT_CONTEXT);

      return value.multiply(currencyBasedRate, DEFAULT_CONTEXT);
   }

}
