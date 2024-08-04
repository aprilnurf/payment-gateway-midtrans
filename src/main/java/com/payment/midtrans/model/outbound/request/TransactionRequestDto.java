package com.payment.midtrans.model.outbound.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionRequestDto {

    @JsonProperty("transaction_details")
    private TransactionDetailDto transactionDetail;
    @JsonProperty("credit_card")
    private CreditCardDto creditCard;
}
