package com.payment.midtrans.outbound;

import com.payment.midtrans.model.outbound.request.TransactionRequestDto;
import com.payment.midtrans.model.outbound.response.TransactionCancelDto;
import com.payment.midtrans.model.outbound.response.TransactionResponseDto;
import com.payment.midtrans.model.outbound.response.TransactionStatusDto;
import reactor.core.publisher.Mono;

public interface SnapPaymentOutboundService {
    Mono<TransactionResponseDto> createPayment(TransactionRequestDto transactionRequestDto);
    Mono<TransactionStatusDto> statusPayment(String orderId);
    Mono<TransactionCancelDto> cancelPayment(String orderId);
}
