package com.payment.midtrans.libraries;

import com.payment.midtrans.model.BaseResponse;

import java.util.Date;
import java.util.List;

public class ResponseHelper {

    public static <T> BaseResponse<T> constructResponse(String code, String message, List<String> errors,
                                                        T data) {
        return BaseResponse.<T>builder()
                .code(code)
                .message(message)
                .data(data)
                .errors(errors)
                .serverTime(new Date())
                .build();
    }}
