package com.payment.midtrans.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.*;

@Slf4j
@Configuration
@ConditionalOnClass(OkHttpClient.class)
public class OkHttpConfiguration {

    @Bean(name = "midtransHttpClient")
    public OkHttpClient midtransHttpClient(MidtransProperties midtransProperties) {
        return getOkHttpClient(midtransProperties);
    }

    private OkHttpClient getOkHttpClient(OutboundProperties outboundProperties) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.valueOf(outboundProperties.getLogLevel()));
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .sslSocketFactory(getSsl(), (X509TrustManager) getTrustAllCerts()[0])
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(outboundProperties.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(outboundProperties.getReadTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(outboundProperties.getReadTimeout(), TimeUnit.MILLISECONDS);

        builder.addInterceptor(
                chain -> chain.proceed(chain.request().newBuilder()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .build()));

        return builder.build();
    }

    private SSLSocketFactory getSsl() {
        SSLSocketFactory sslSocketFactory = null;
        try {
            final TrustManager[] trustAllCerts = getTrustAllCerts();
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            log.error("error get ssl {}", e.getMessage(), e);
        }

        return sslSocketFactory;
    }

    private TrustManager[] getTrustAllCerts() {
        return new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
                                                   String authType)
                            throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
                                                   String authType)
                            throws CertificateException {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };
    }
}
