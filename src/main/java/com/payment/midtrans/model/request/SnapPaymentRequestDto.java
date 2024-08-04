package com.payment.midtrans.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SnapPaymentRequestDto {
    @NotBlank
    private String orderId;
    @NotNull
    private double amount;
}
