package com.payment.midtrans.service.impl;

import com.payment.midtrans.model.outbound.request.CreditCardDto;
import com.payment.midtrans.model.outbound.request.TransactionDetailDto;
import com.payment.midtrans.model.outbound.request.TransactionRequestDto;
import com.payment.midtrans.model.request.SnapPaymentRequestDto;
import com.payment.midtrans.model.response.SnapPaymentResponseDto;
import com.payment.midtrans.outbound.SnapPaymentOutboundService;
import com.payment.midtrans.service.SnapPaymentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class SnapPaymentServiceImpl implements SnapPaymentService {

    private final SnapPaymentOutboundService snapPaymentOutboundService;

    @Autowired
    public SnapPaymentServiceImpl(SnapPaymentOutboundService snapPaymentOutboundService) {
        this.snapPaymentOutboundService = snapPaymentOutboundService;
    }

    @Override
    public Mono<SnapPaymentResponseDto> createPayment(SnapPaymentRequestDto requestDto) {
        return Mono.defer(() -> {
            var request = TransactionRequestDto.builder()
                    .transactionDetail(TransactionDetailDto.builder()
                            .orderId(requestDto.getOrderId())
                            .amount(requestDto.getAmount())
                            .build())
                    .creditCard(CreditCardDto.builder().secure(true).build())
                    .build();
            return Mono.defer(() -> snapPaymentOutboundService.createPayment(request)
                    .map(data -> SnapPaymentResponseDto.builder()
                            .url(data.getRedirectUrl())
                            .orderId(requestDto.getOrderId())
                            .build())
                    .doOnError(e -> log.error("Error create payment on orderId {} ", request.getTransactionDetail().getOrderId(), e)));
        });
    }

    @Override
    public Mono<Boolean> status(String orderId) {
        return Mono.defer(() -> {
            return snapPaymentOutboundService.statusPayment(orderId)
                    .map(data -> "success".equalsIgnoreCase(data.getTransactionStatus()))
                    .doOnError(e -> {
                        log.error("Error get status payment on orderId {} ", orderId, e);
                    });
        });
    }

    @Override
    public Mono<Boolean> cancel(String orderId) {
        return Mono.defer(() -> snapPaymentOutboundService.cancelPayment(orderId)
                .map(data -> "cancel".equalsIgnoreCase(data.getTransactionStatus()))
                .doOnError(e -> log.error("Error cancel payment on orderId {} ", orderId, e)));
    }
}
