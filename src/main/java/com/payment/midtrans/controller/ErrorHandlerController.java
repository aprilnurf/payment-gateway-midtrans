package com.payment.midtrans.controller;

import com.payment.midtrans.libraries.CustomException;
import com.payment.midtrans.model.BaseResponse;
import com.payment.midtrans.model.constant.ResponseCode;
import com.payment.midtrans.libraries.ResponseHelper;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Log4j2
public class ErrorHandlerController {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public BaseResponse handleError(BindException ex) {
        log.error("Error during validation", ex);
        List<FieldError> fieldErrors = ex.getFieldErrors();
        List<String> errors = fieldErrors.stream().map(e-> e.getField() + " " +e.getDefaultMessage()).toList();
        return ResponseHelper.constructResponse(
                ResponseCode.BIND_ERROR.getCode(),
                ResponseCode.BIND_ERROR.getMessage(),
                errors,
                null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse exception(Exception e) {
        log.error("Exception = {}", e.getMessage(), e);
        return ResponseHelper.constructResponse(ResponseCode.SYSTEM_ERROR.getCode(),
                ResponseCode.SYSTEM_ERROR.getMessage(), null, null);
    }

    @ExceptionHandler(CustomException.class)
    public BaseResponse businessLogicException(CustomException ce) {
        log.error("BusinessLogicException = {}", ce.getMessage(), ce);
        return ResponseHelper.constructResponse(ce.getCode(), ce.getMessage(), null, null);
    }
}
