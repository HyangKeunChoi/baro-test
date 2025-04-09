package com.example.barotest.common.exception;

import com.example.barotest.common.response.ErrorInfo;

public class DeliveryStatusException extends BaseException {
    public DeliveryStatusException() {
        super(ErrorInfo.DELIVERY_ADDRESS_UPDATE_ERROR);
    }
}
