package com.example.barotest.common.exception;

import com.example.barotest.common.response.ErrorInfo;

public class DeliveryNotFoundException extends BaseException {
    public DeliveryNotFoundException() {
        super(ErrorInfo.DELIVERY_NOT_FOUND_ERROR);
    }
}
