package com.payment.midtrans.model.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS("SUCCESS", "SUCCESS"),
    SERVICE_UNAVAILABLE("SERVICE_UNAVAILABLE", "Service unavailable"),
    BIND_ERROR("BIND_ERROR", "Please fill in mandatory parameter"),
    SYSTEM_ERROR("SYSTEM_ERROR", "System error"),
    FAILED("FAILED", "Failed error"),
    THIRD_PARTY_ERROR("THIRD_PARTY_ERROR", "Third party error");

    private String code;
    private String message;

}
