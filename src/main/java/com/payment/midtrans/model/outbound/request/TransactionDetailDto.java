package com.payment.midtrans.model.outbound.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDetailDto {

    @JsonProperty("order_id")
    private String orderId;
    @JsonProperty("gross_amount")
    private double amount;
}
