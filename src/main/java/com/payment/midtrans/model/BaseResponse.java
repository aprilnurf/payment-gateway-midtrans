package com.payment.midtrans.model;

import lombok.Builder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Builder
public class BaseResponse <T> implements Serializable {
    private String code;
    private String message;
    private T data;
    private List<String> errors;
    private Date serverTime;
}
