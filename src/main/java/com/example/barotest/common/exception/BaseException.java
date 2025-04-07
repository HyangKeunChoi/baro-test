package com.example.barotest.common.exception;

import com.example.barotest.common.response.ErrorInfo;
import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {
    private ErrorInfo errorInfo;

    public BaseException(ErrorInfo errorInfo) {
        super(errorInfo.getErrorMsg());
        this.errorInfo = errorInfo;
    }

    public BaseException(String message, ErrorInfo errorInfo) {
        super(message);
        this.errorInfo = errorInfo;
    }
}
