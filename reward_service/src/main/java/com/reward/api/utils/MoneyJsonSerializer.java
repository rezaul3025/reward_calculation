package com.reward.api.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class MoneyJsonSerializer extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal value, JsonGenerator jsonGen, SerializerProvider serializers) throws IOException {
        jsonGen.writeString(value.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
    }
}
