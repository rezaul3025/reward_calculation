package com.reward.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Configuration
public class ConfigServiceHandler implements ConfigService{

    private final MathContext DEFAULT_CONTEXT = new MathContext(12, RoundingMode.HALF_DOWN);

    @Value("${reward.price.per1000.steps}")
    BigDecimal rewardPricePer1000Steps;

    @Value("${rewarded.steps.threshold}")
    Integer rewardedStepThreshold;

    @Value("${base.currency.iso.code}")
    String baseCurrencyIsoCode;


    @Override
    public MathContext getDefaultContext() {
        return DEFAULT_CONTEXT;
    }

    @Override
    public BigDecimal getRewardPricePer1000Steps() {
        return rewardPricePer1000Steps;
    }

    @Override
    public Integer getRewardedStepThreshold() {
        return rewardedStepThreshold;
    }

    @Override
    public String getBaseCurrencyIsoCode() {
        return baseCurrencyIsoCode;
    }
}
