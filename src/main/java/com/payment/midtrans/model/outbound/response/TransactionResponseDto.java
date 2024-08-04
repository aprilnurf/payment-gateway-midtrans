package com.payment.midtrans.model.outbound.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionResponseDto {
   private String token;
   @JsonProperty( "redirect_url")
   private String redirectUrl;
}
