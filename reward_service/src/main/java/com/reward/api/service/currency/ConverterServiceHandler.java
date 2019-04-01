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

   /**
    *
    * @param value
    * @param fromCurrency
    * @param toCurrency
    * @return
    */

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

   /**
    * Convert to USD is needed because open exchange API free plan only provide USD based exchange rate, So we can use
    * USD exchange rate in order find given currency rate
    *
    * @param value
    * @param currency
    * @return
    */

   @Override
   public BigDecimal convertToUSD(@NotNull BigDecimal value, @NotNull String currency)
   {
      //Get USD based rate
      BigDecimal rate = exchangeRateService.getExchangeRateOf(currency);

      //Find given currency rate
      BigDecimal currencyBasedRate =  BigDecimal.ONE.divide(rate, configService.getDefaultContext());

      //Covert to USD
      return value.multiply(currencyBasedRate, configService.getDefaultContext());
   }

}
