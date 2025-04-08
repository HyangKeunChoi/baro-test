package com.example.barotest.common.exception;

import com.example.barotest.common.response.ErrorInfo;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super(ErrorInfo.USER_NOT_FOUND_ERROR);
    }
}
