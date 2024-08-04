package com.payment.midtrans.model.outbound.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionCancelDto extends BaseTransactionResponseDto {

}
