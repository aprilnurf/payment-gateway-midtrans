package com.payment.midtrans.outbound.endpoint;

import com.payment.midtrans.model.constant.ApiPath;
import com.payment.midtrans.model.outbound.request.TransactionRequestDto;
import com.payment.midtrans.model.outbound.response.TransactionCancelDto;
import com.payment.midtrans.model.outbound.response.TransactionResponseDto;
import com.payment.midtrans.model.outbound.response.TransactionStatusDto;
import reactor.core.publisher.Mono;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.Map;

public interface MidtransEndpointService {

    @POST(ApiPath.TRANSACTIONS)
    Mono<Response<TransactionResponseDto>> createTransaction(
            @HeaderMap Map<String, String> header,
            @Body TransactionRequestDto transactionRequestDto);

    @GET(ApiPath.STATUS)
    Mono<Response<TransactionStatusDto>> statusTransaction(
            @HeaderMap Map<String, String> header,
            @Path("order_id") String orderId);

    @GET(ApiPath.CANCEL)
    Mono<Response<TransactionCancelDto>> cancelTransaction(
            @HeaderMap Map<String, String> header,
            @Path("order_id") String orderId);

}
