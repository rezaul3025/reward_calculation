package com.reward.api.service.currency;


import com.reward.api.service.external.OpenExchangeApiService;
import com.reward.api.service.external.mock.MockOpenExchangeApiService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeRateServiceHandlerTest
{
   @Mock
   OpenExchangeApiService openExchangeApiService;

   @Before
   public void setUp() throws Exception
   {
      MockOpenExchangeApiService mockOpenExchangeApiService = new MockOpenExchangeApiService();
      when(openExchangeApiService.getExchangeRateApiResponse()).thenReturn(mockOpenExchangeApiService.getExchangeRateApiResponse());
   }

   @Test
   public void getEURBasedRate()
   {
      ExchangeRateServiceHandler exchangeRateServiceHandler = new ExchangeRateServiceHandler(openExchangeApiService);
      exchangeRateServiceHandler.getExchangeRates();

      BigDecimal gbpRate = exchangeRateServiceHandler.getExchangeRateOf("GBP");

      assertThat(gbpRate).isNotNegative().isNotNull();
      assertThat(gbpRate.doubleValue()).isEqualTo(0.758171);
   }
}