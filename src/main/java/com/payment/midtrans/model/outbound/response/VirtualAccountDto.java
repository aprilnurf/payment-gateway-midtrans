package com.payment.midtrans.model.outbound.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VirtualAccountDto {

    private String bank;
    @JsonProperty("va_number")
    private String va;
}
