package com.payment.midtrans.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OutboundProperties {

    private Integer baseHttpClientTotalMax;
    private Integer baseHttpClientTotalPerRoute;
    private Integer connectTimeout;
    private Integer readTimeout;
    private String host;
    private String app;
    private String proxyHost;
    private int proxyPort;
    private String proxyUsername;
    private String proxyPassword;
    private int proxyUse;
    private String logLevel;
}
