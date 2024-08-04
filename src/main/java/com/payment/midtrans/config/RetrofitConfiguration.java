package com.payment.midtrans.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jakewharton.retrofit2.adapter.reactor.ReactorCallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import java.util.Objects;

public class RetrofitConfiguration {

    protected Retrofit getRetrofit(OutboundProperties outboundProperties, OkHttpClient okHttpClient ) {
        Retrofit.Builder builder = new Retrofit.Builder();
        if (Objects.isNull(okHttpClient)) {
            builder.client(okHttpClient);
        }
        builder.baseUrl(outboundProperties.getHost());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        builder.addConverterFactory(JacksonConverterFactory.create(objectMapper)).addCallAdapterFactory(ReactorCallAdapterFactory.create());
        return builder.build();
    }
}
