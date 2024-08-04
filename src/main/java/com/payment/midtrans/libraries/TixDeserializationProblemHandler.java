package com.payment.midtrans.libraries;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class TixDeserializationProblemHandler extends DeserializationProblemHandler {

    @Override
    public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser jp,
                                         JsonDeserializer<?> deserializer, Object beanOrClass, String propertyName)
            throws IOException {
        log.warn("unknown field `{}` from object {}", propertyName, beanOrClass);
        return true;
    }
}
