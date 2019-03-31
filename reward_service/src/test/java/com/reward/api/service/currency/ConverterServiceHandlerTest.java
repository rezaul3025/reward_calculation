package com.reward.api.service.currency;


import com.reward.api.config.ConfigService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConverterServiceHandlerTest
{
   @Mock
   private ConfigService configService;

   @Mock
   private ExchangeRateService exchangeRateService;

   @InjectMocks
   private ConverterServiceHandler converterServiceHandler;

   private final MathContext DEFAULT_CONTEXT = new MathContext(12, RoundingMode.HALF_DOWN);

   @Before
   public void setUp(){
      when(exchangeRateService.getExchangeRateOf("EUR")).thenReturn(new BigDecimal(0.88838));
      when(exchangeRateService.getExchangeRateOf("GBP")).thenReturn(new BigDecimal(0.758171));
      when(exchangeRateService.getExchangeRateOf("BDT")).thenReturn(new BigDecimal(84.219239));
      when(configService.getDefaultContext()).thenReturn(DEFAULT_CONTEXT);
   }

   @Test
   public void convertTest()
   {
      BigDecimal amount = new BigDecimal(100);
      BigDecimal val =converterServiceHandler.convert(amount, "EUR", "GBP");

      assertThat(val).isNotNull();
      assertThat(val.doubleValue()).isEqualTo(85.3430964229);
   }

   @Test
   public void convertCheckSameCurrencyTest()
   {
      BigDecimal amount = new BigDecimal(100);
      BigDecimal val =converterServiceHandler.convert(amount, "EUR", "EUR");

      assertThat(val).isNotNull();
      assertThat(val.doubleValue()).isEqualTo(100);
   }

   @Test
   public void convertToUsdTest()
   {
      BigDecimal amount = converterServiceHandler.convertToUSD(new BigDecimal(200.00), "BDT");

      assertThat(amount).isNotNull();
      assertThat(amount.doubleValue()).isEqualTo(2.37475430050);
   }
}