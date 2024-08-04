package com.payment.midtrans.service;

import com.payment.midtrans.model.request.SnapPaymentRequestDto;
import com.payment.midtrans.model.response.SnapPaymentResponseDto;
import reactor.core.publisher.Mono;

public interface SnapPaymentService {

    Mono<SnapPaymentResponseDto> createPayment(SnapPaymentRequestDto requestDto);
    Mono<Boolean> status(String orderId);
    Mono<Boolean> cancel(String orderId);

}
