package com.payment.midtrans.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "payment.gateway.midtrans")
public class MidtransProperties extends OutboundProperties {
}
