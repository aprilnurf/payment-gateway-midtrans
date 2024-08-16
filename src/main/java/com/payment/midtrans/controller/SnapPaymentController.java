package com.payment.midtrans.controller;

import com.payment.midtrans.model.BaseResponse;
import com.payment.midtrans.model.constant.ApiPath;
import com.payment.midtrans.model.constant.ResponseCode;
import com.payment.midtrans.model.request.SnapPaymentRequestDto;
import com.payment.midtrans.model.response.SnapPaymentResponseDto;
import com.payment.midtrans.service.SnapPaymentService;
import com.payment.midtrans.libraries.ResponseHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiPath.SNAP)
public class SnapPaymentController extends ErrorHandlerController {

    private final SnapPaymentService snapPaymentService;

    @Autowired
    public SnapPaymentController(SnapPaymentService snapPaymentService) {
        this.snapPaymentService = snapPaymentService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<BaseResponse<SnapPaymentResponseDto>> payment(@RequestBody @Valid SnapPaymentRequestDto request) {
        return snapPaymentService.createPayment(request)
                .map(response -> ResponseHelper.constructResponse(
                        ResponseCode.SUCCESS.getCode(),
                        ResponseCode.SUCCESS.getMessage(),
                        null,
                        response));
    }

    @GetMapping(value = "/status/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Mono<BaseResponse<Boolean>> paymentStatus(@PathVariable("orderId") String orderId) {
        return snapPaymentService.status(orderId)
                .map(response -> ResponseHelper.constructResponse(
                        ResponseCode.SUCCESS.getCode(),
                        ResponseCode.SUCCESS.getMessage(),
                        null,
                        response));
    }
}
