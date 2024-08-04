package com.payment.midtrans.model.outbound.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseTransactionResponseDto {
    @JsonProperty("status_code")
    private String statusCode;
    @JsonProperty("status_message")
    private String statusMessage;
    @JsonProperty("transaction_id")
    private String transactionId;
    @JsonProperty("gross_amount")
    private String amount;
    private String currency;
    @JsonProperty("order_id")
    private String orderId;
    @JsonProperty("merchant_id")
    private String merchantId;
    @JsonProperty("payment_type")
    private String paymentType;
    @JsonProperty("transaction_status")
    private String transactionStatus;
    @JsonProperty("fraud_status")
    private String fraudStatus;
    @JsonProperty("transaction_time")
    private String transactionTime;
}
