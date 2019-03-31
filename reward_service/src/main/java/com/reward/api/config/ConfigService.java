package com.reward.api.config;

import java.math.BigDecimal;
import java.math.MathContext;

public interface ConfigService {

    MathContext getDefaultContext();

    BigDecimal getRewardPricePer1000Steps();

    Integer getRewardedStepThreshold();

    String getBaseCurrencyIsoCode();
}
