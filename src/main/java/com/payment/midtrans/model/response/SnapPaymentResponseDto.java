package com.payment.midtrans.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class SnapPaymentResponseDto implements Serializable {
    private String url;
    private String orderId;
}
