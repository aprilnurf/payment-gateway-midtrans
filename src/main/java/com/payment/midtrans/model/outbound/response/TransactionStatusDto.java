package com.payment.midtrans.model.outbound.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionStatusDto extends BaseTransactionResponseDto{

    @JsonProperty("signature_key")
    private String signatureKey;
    @JsonProperty("transaction_time")
    private String transactionTime;
    @JsonProperty("settlement_time")
    private String settlementTime;
    @JsonProperty("expiry_time")
    private String expiryTime;
    @JsonProperty("va_numbers")
    private List<VirtualAccountDto> virtualAccounts;
}
