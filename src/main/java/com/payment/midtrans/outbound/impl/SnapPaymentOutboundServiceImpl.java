package com.payment.midtrans.outbound.impl;

import com.payment.midtrans.libraries.CustomException;
import com.payment.midtrans.model.constant.ResponseCode;
import com.payment.midtrans.model.outbound.request.TransactionRequestDto;
import com.payment.midtrans.model.outbound.response.TransactionCancelDto;
import com.payment.midtrans.model.outbound.response.TransactionResponseDto;
import com.payment.midtrans.model.outbound.response.TransactionStatusDto;
import com.payment.midtrans.outbound.SnapPaymentOutboundService;
import com.payment.midtrans.outbound.endpoint.MidtransEndpointService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class SnapPaymentOutboundServiceImpl implements SnapPaymentOutboundService {

    private final MidtransEndpointService midtransEndpointService;
    @Value("${payment.gateway.midtrans.authKey}")
    private String authKey;

    @Autowired
    public SnapPaymentOutboundServiceImpl(MidtransEndpointService midtransEndpointService) {
        this.midtransEndpointService = midtransEndpointService;
    }

    @Override
    public Mono<TransactionResponseDto> createPayment(TransactionRequestDto requestDto) {
        return Mono.defer(() -> {
            Map<String, String> header = headerBuilder();
            return midtransEndpointService.createTransaction(header, requestDto)
                    .<TransactionResponseDto>handle((response, sink) -> {
                        if (response.code() != 201 || response.body() == null) {
                            log.error("Payment status not created on orderID {} ", requestDto);
                            sink.error(new CustomException(
                                    ResponseCode.FAILED.getCode(),
                                    ResponseCode.FAILED.getMessage()));
                            return;
                        }
                        sink.next(response.body());
                    })
                    .onErrorResume(e -> {
                        log.error("Error occurred during status payment request ", e);
                        return Mono.error(new CustomException(
                                ResponseCode.THIRD_PARTY_ERROR.getCode(),
                                ResponseCode.THIRD_PARTY_ERROR.getMessage()));
                    });
        });
    }

    @Override
    public Mono<TransactionStatusDto> statusPayment(String orderId) {
        return Mono.defer(() -> {
            Map<String, String> header = headerBuilder();
            return midtransEndpointService.statusTransaction(header, orderId)
                    .<TransactionStatusDto>handle((response, sink) -> {
                        if (response.code() != 200 || response.body() == null) {
                            log.error("Payment status not created on orderID {} ", orderId);
                            sink.error(new CustomException(
                                    ResponseCode.FAILED.getCode(),
                                    ResponseCode.FAILED.getMessage()));
                            return;
                        }
                        sink.next(response.body());
                    })
                    .onErrorResume(e -> {
                        log.error("Error occurred during status payment request ", e);
                        return Mono.error(new CustomException(
                                ResponseCode.THIRD_PARTY_ERROR.getCode(),
                                ResponseCode.THIRD_PARTY_ERROR.getMessage()));
                    });

        });
    }

    @Override
    public Mono<TransactionCancelDto> cancelPayment(String orderId) {
        return Mono.defer(() -> {
            Map<String, String> header = headerBuilder();
            return midtransEndpointService.cancelTransaction(header, orderId)
                    .<TransactionCancelDto>handle((response, sink) -> {
                        if (response.code() != 200 || response.body() == null) {
                            log.error("Payment status failed to cancel on orderID {} ", orderId);
                            sink.error(new CustomException(
                                    ResponseCode.FAILED.getCode(),
                                    ResponseCode.FAILED.getMessage()));
                            return;
                        }
                        sink.next(response.body());
                    })
                    .onErrorResume(e -> {
                        log.error("Error occurred during cancel payment request ", e);
                        return Mono.error(new CustomException(
                                ResponseCode.THIRD_PARTY_ERROR.getCode(),
                                ResponseCode.THIRD_PARTY_ERROR.getMessage()));
                    });
        });
    }

    private Map<String, String> headerBuilder() {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", authKey);
        header.put("Content-Type", "application/json");
        header.put("Accept", "application/json");
        return header;
    }
}
