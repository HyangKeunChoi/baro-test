package com.example.barotest.common.exception;

import com.example.barotest.common.response.ErrorInfo;

public class PasswordLengthException extends BaseException {
    public PasswordLengthException() {
        super(ErrorInfo.PASSWORD_LENGTH_ERROR);
    }
}
