package com.reward.api.service.currency;

import com.reward.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Service
public class ConverterServiceHandler implements ConverterService
{
   private ConfigService configService;

   private ExchangeRateService exchangeRateService;

   @Autowired
   public ConverterServiceHandler(ConfigService configService, ExchangeRateService exchangeRateService){
      this.configService = configService;
      this.exchangeRateService = exchangeRateService;
   }

   @Override
   public BigDecimal convert(@NotNull BigDecimal value, @NotNull String fromCurrency, @NotNull String toCurrency)
   {
      if(fromCurrency.equals(toCurrency)){
         return value;
      }

      BigDecimal usdValue = convertToUSD(value, fromCurrency);

      BigDecimal rate = exchangeRateService.getExchangeRateOf(toCurrency);

      return  usdValue.multiply(rate, configService.getDefaultContext());
   }

   @Override
   public BigDecimal convertToUSD(@NotNull BigDecimal value, @NotNull String currency)
   {
      BigDecimal rate = exchangeRateService.getExchangeRateOf(currency);

      BigDecimal currencyBasedRate =  BigDecimal.ONE.divide(rate, configService.getDefaultContext());

      return value.multiply(currencyBasedRate, configService.getDefaultContext());
   }

}
