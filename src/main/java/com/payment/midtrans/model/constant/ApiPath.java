package com.payment.midtrans.model.constant;

public interface ApiPath {

    //outbound
    String BASE_OUTBOUND_PATH = "https://app.sandbox.midtrans.com/";
    String BASE_OUTBOUND_PATH_2 = "https://api.sandbox.midtrans.com/";
    String TRANSACTIONS = "snap/v1/transactions";
    String V2_ID = "v2/{order_id}/";
    String STATUS = BASE_OUTBOUND_PATH_2 + V2_ID + "status";
    String DENY = BASE_OUTBOUND_PATH + V2_ID + "deny";
    String CANCEL = V2_ID + "cancel";
    String REFUND = BASE_OUTBOUND_PATH + V2_ID + "refund";

    //rest
    String BASE_PATH = "payment/v1/";
    String PAYMENT = BASE_PATH + "payment";
    String SNAP = BASE_PATH + "snap";
}
