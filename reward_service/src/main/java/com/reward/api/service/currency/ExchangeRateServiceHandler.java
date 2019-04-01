package com.reward.api.service.currency;

import com.reward.api.service.external.OpenExchangeApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

@Service
public class ExchangeRateServiceHandler implements  ExchangeRateService
{
   static final Logger LOG = Logger.getLogger(ExchangeRateServiceHandler.class.getName());

   private final Map<String, BigDecimal> exchangeRateMap = new ConcurrentHashMap<>();

   private OpenExchangeApiService openExchangeApiService;

   @Autowired
   public ExchangeRateServiceHandler(OpenExchangeApiService openExchangeApiService){
      this.openExchangeApiService = openExchangeApiService;
   }


   /**
    * Update exchange rate every hour
    */
   @Override
   @Scheduled(cron = "0 0/60 * * * ?")
   public void getExchangeRates()
   {
      String response = parseResponse(openExchangeApiService.getExchangeRateApiResponse().getBody());

      String[] exchangeRateList = response.split(",");

      for(String exchangeRate : exchangeRateList){

         String[] exchangeRateArr = exchangeRate.split(":");

         String currency = exchangeRateArr[0].trim();

         BigDecimal rate = new  BigDecimal(exchangeRateArr[1].trim());

         exchangeRateMap.put(currency, rate);
      }

      LOG.info("Exchange rates updated successfully");
   }

   /**
    * Get exchange rate of given currency
    *
    * @param currency
    * @return
    */
   @Override
   public BigDecimal getExchangeRateOf(@NotNull String currency)
   {
      //Call exchange API directly, when its not available
      if(exchangeRateMap.isEmpty()) {
         getExchangeRates();
      }

      BigDecimal rate = exchangeRateMap.get(currency);

      if(rate == null){
         return BigDecimal.ZERO;
      }

      return rate;
   }

   private String parseResponse(@NotNull String response)
   {
      response = response.replaceAll("\"","").replaceAll("\\n","");
      response = response.substring(response.indexOf("rates:")+"rates:".length());
      response = response.replaceAll("\\{","").replaceAll("\\}", "").trim();

      return response;
   }
}
