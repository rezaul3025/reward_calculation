package com.reward.api.service.currency;


import com.reward.api.service.external.OpenExchangeApiService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

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
      when(openExchangeApiService.getExchangeRateApiResponse()).thenReturn(ResponseEntity.ok(apiResponse));
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

   private final String apiResponse="{\n" +
           "  \"disclaimer\": \"Usage subject to terms: https://openexchangerates.org/terms\",\n" +
           "  \"license\": \"https://openexchangerates.org/license\",\n" +
           "  \"timestamp\": 1553756400,\n" +
           "  \"base\": \"USD\",\n" +
           "  \"rates\": {\n" +
           "    \"AED\": 3.673097,\n" +
           "    \"AFN\": 76.023182,\n" +
           "    \"ALL\": 111.33,\n" +
           "    \"AMD\": 488.033886,\n" +
           "    \"ANG\": 1.850333,\n" +
           "    \"AOA\": 317.2565,\n" +
           "    \"ARS\": 43.874,\n" +
           "    \"AUD\": 1.408425,\n" +
           "    \"AWG\": 1.800999,\n" +
           "    \"AZN\": 1.7025,\n" +
           "    \"BAM\": 1.733954,\n" +
           "    \"BBD\": 2,\n" +
           "    \"BDT\": 84.219239,\n" +
           "    \"BGN\": 1.737458,\n" +
           "    \"BHD\": 0.377015,\n" +
           "    \"BIF\": 1811.554385,\n" +
           "    \"BMD\": 1,\n" +
           "    \"BND\": 1.350854,\n" +
           "    \"BOB\": 6.904391,\n" +
           "    \"BRL\": 3.99235,\n" +
           "    \"BSD\": 1,\n" +
           "    \"BTC\": 0.000248660559,\n" +
           "    \"BTN\": 69.074618,\n" +
           "    \"BWP\": 10.786942,\n" +
           "    \"BYN\": 2.13505,\n" +
           "    \"BZD\": 2.020872,\n" +
           "    \"CAD\": 1.341004,\n" +
           "    \"CDF\": 1641.07388,\n" +
           "    \"CHF\": 0.99539,\n" +
           "    \"CLF\": 0.024214,\n" +
           "    \"CLP\": 681.500729,\n" +
           "    \"CNH\": 6.736837,\n" +
           "    \"CNY\": 6.7284,\n" +
           "    \"COP\": 3162.778774,\n" +
           "    \"CRC\": 607.08667,\n" +
           "    \"CUC\": 1,\n" +
           "    \"CUP\": 25.75,\n" +
           "    \"CVE\": 98.1185,\n" +
           "    \"CZK\": 22.884335,\n" +
           "    \"DJF\": 178,\n" +
           "    \"DKK\": 6.632928,\n" +
           "    \"DOP\": 51.061291,\n" +
           "    \"DZD\": 119.201297,\n" +
           "    \"EGP\": 17.3133,\n" +
           "    \"ERN\": 14.996927,\n" +
           "    \"ETB\": 28.652987,\n" +
           "    \"EUR\": 0.88838,\n" +
           "    \"FJD\": 2.126404,\n" +
           "    \"FKP\": 0.758171,\n" +
           "    \"GBP\": 0.758171,\n" +
           "    \"GEL\": 2.69,\n" +
           "    \"GGP\": 0.758171,\n" +
           "    \"GHS\": 5.209271,\n" +
           "    \"GIP\": 0.758171,\n" +
           "    \"GMD\": 49.6775,\n" +
           "    \"GNF\": 9146.999945,\n" +
           "    \"GTQ\": 7.698714,\n" +
           "    \"GYD\": 208.793576,\n" +
           "    \"HKD\": 7.85015,\n" +
           "    \"HNL\": 24.51358,\n" +
           "    \"HRK\": 6.597,\n" +
           "    \"HTG\": 83.993808,\n" +
           "    \"HUF\": 284.22,\n" +
           "    \"IDR\": 14253.5,\n" +
           "    \"ILS\": 3.632003,\n" +
           "    \"IMP\": 0.758171,\n" +
           "    \"INR\": 68.955,\n" +
           "    \"IQD\": 1196.608224,\n" +
           "    \"IRR\": 42105,\n" +
           "    \"ISK\": 121.230248,\n" +
           "    \"JEP\": 0.758171,\n" +
           "    \"JMD\": 125.79812,\n" +
           "    \"JOD\": 0.709001,\n" +
           "    \"JPY\": 110.122,\n" +
           "    \"KES\": 100.8,\n" +
           "    \"KGS\": 68.688821,\n" +
           "    \"KHR\": 4059.175701,\n" +
           "    \"KMF\": 437.481982,\n" +
           "    \"KPW\": 900,\n" +
           "    \"KRW\": 1137.36,\n" +
           "    \"KWD\": 0.303871,\n" +
           "    \"KYD\": 0.835767,\n" +
           "    \"KZT\": 380.313464,\n" +
           "    \"LAK\": 8616.474564,\n" +
           "    \"LBP\": 1512.598699,\n" +
           "    \"LKR\": 176.16,\n" +
           "    \"LRD\": 162.374667,\n" +
           "    \"LSL\": 14.621679,\n" +
           "    \"LYD\": 1.39141,\n" +
           "    \"MAD\": 9.6508,\n" +
           "    \"MDL\": 17.31011,\n" +
           "    \"MGA\": 3556.20871,\n" +
           "    \"MKD\": 54.673307,\n" +
           "    \"MMK\": 1524.958192,\n" +
           "    \"MNT\": 2512.518323,\n" +
           "    \"MOP\": 8.107669,\n" +
           "    \"MRO\": 357,\n" +
           "    \"MRU\": 36.56,\n" +
           "    \"MUR\": 34.859,\n" +
           "    \"MVR\": 15.400001,\n" +
           "    \"MWK\": 724.51141,\n" +
           "    \"MXN\": 19.388805,\n" +
           "    \"MYR\": 4.074981,\n" +
           "    \"MZN\": 63.620084,\n" +
           "    \"NAD\": 14.41,\n" +
           "    \"NGN\": 362.535494,\n" +
           "    \"NIO\": 32.918555,\n" +
           "    \"NOK\": 8.62841,\n" +
           "    \"NPR\": 110.530976,\n" +
           "    \"NZD\": 1.465513,\n" +
           "    \"OMR\": 0.385052,\n" +
           "    \"PAB\": 1,\n" +
           "    \"PEN\": 3.316017,\n" +
           "    \"PGK\": 3.384502,\n" +
           "    \"PHP\": 52.784376,\n" +
           "    \"PKR\": 140.56,\n" +
           "    \"PLN\": 3.814677,\n" +
           "    \"PYG\": 6167.588844,\n" +
           "    \"QAR\": 3.640829,\n" +
           "    \"RON\": 4.226385,\n" +
           "    \"RSD\": 104.735095,\n" +
           "    \"RUB\": 64.8284,\n" +
           "    \"RWF\": 905.290538,\n" +
           "    \"SAR\": 3.7502,\n" +
           "    \"SBD\": 8.152405,\n" +
           "    \"SCR\": 13.661987,\n" +
           "    \"SDG\": 47.754947,\n" +
           "    \"SEK\": 9.273814,\n" +
           "    \"SGD\": 1.354523,\n" +
           "    \"SHP\": 0.758171,\n" +
           "    \"SLL\": 8390,\n" +
           "    \"SOS\": 580.166206,\n" +
           "    \"SRD\": 7.458,\n" +
           "    \"SSP\": 130.2634,\n" +
           "    \"STD\": 21050.59961,\n" +
           "    \"STN\": 21.775,\n" +
           "    \"SVC\": 8.775535,\n" +
           "    \"SYP\": 515.003445,\n" +
           "    \"SZL\": 14.679827,\n" +
           "    \"THB\": 31.84,\n" +
           "    \"TJS\": 9.457483,\n" +
           "    \"TMT\": 3.50998,\n" +
           "    \"TND\": 3.027435,\n" +
           "    \"TOP\": 2.25566,\n" +
           "    \"TRY\": 5.455344,\n" +
           "    \"TTD\": 6.795112,\n" +
           "    \"TWD\": 30.87289,\n" +
           "    \"TZS\": 2340.55,\n" +
           "    \"UAH\": 27.166534,\n" +
           "    \"UGX\": 3716.42587,\n" +
           "    \"USD\": 1,\n" +
           "    \"UYU\": 33.457823,\n" +
           "    \"UZS\": 8409.868393,\n" +
           "    \"VEF\": 248487.642241,\n" +
           "    \"VES\": 3288.734148,\n" +
           "    \"VND\": 23248.000413,\n" +
           "    \"VUV\": 111.118376,\n" +
           "    \"WST\": 2.596857,\n" +
           "    \"XAF\": 582.738786,\n" +
           "    \"XAG\": 0.06531263,\n" +
           "    \"XAU\": 0.00076303,\n" +
           "    \"XCD\": 2.7026,\n" +
           "    \"XDR\": 0.716926,\n" +
           "    \"XOF\": 582.738786,\n" +
           "    \"XPD\": 0.00069344,\n" +
           "    \"XPF\": 106.01188,\n" +
           "    \"XPT\": 0.00116348,\n" +
           "    \"YER\": 250.350747,\n" +
           "    \"ZAR\": 14.61993,\n" +
           "    \"ZMW\": 12.210598,\n" +
           "    \"ZWL\": 322.355011\n" +
           "  }\n" +
           "}";
}