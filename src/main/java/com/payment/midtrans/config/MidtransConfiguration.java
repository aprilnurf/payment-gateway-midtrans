package com.payment.midtrans.config;

import com.payment.midtrans.outbound.endpoint.MidtransEndpointService;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

@Configuration
@ConditionalOnClass(Retrofit.class)
public class MidtransConfiguration extends RetrofitConfiguration {

    @Bean(name = "retrofitMidtransApi")
    public Retrofit retrofitMidtransApi(MidtransProperties midtransProperties,
                                       @Qualifier("midtransHttpClient") OkHttpClient okHttpClient) {
        return getRetrofit(midtransProperties, okHttpClient);
    }

    @Bean
    public MidtransEndpointService midtransEndpointService(
            @Qualifier("retrofitMidtransApi") Retrofit retrofit) {
        return retrofit.create(MidtransEndpointService.class);
    }
}
