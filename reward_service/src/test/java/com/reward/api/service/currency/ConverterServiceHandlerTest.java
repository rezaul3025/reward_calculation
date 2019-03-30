package com.reward.api.service.currency;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConverterServiceHandlerTest
{
   @Mock
   private ExchangeRateService exchangeRateService;

   @InjectMocks
   private ConverterServiceHandler converterServiceHandler;

   @Before
   public void setUp(){
      when(exchangeRateService.getExchangeRateOf("EUR")).thenReturn(new BigDecimal(0.88838));
      when(exchangeRateService.getExchangeRateOf("GBP")).thenReturn(new BigDecimal(0.758171));
      when(exchangeRateService.getExchangeRateOf("BDT")).thenReturn(new BigDecimal(84.219239));
   }

   @Test
   public void convert()
   {
      BigDecimal amount = new BigDecimal(100.00);
      BigDecimal val =converterServiceHandler.convert(amount, "EUR", "GBP");

      assertThat(val).isNotNull();
      assertThat(val.doubleValue()).isEqualTo(85.3430964229);
   }

   @Test
   public void convertToUSD()
   {
      BigDecimal amount = converterServiceHandler.convertToUSD(new BigDecimal(200.00), "BDT");

      assertThat(amount).isNotNull();
      assertThat(amount.doubleValue()).isEqualTo(2.37475430050);
   }
}