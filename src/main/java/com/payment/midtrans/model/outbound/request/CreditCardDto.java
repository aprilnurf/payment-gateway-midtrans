package com.payment.midtrans.model.outbound.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreditCardDto {

    private boolean secure;
}
