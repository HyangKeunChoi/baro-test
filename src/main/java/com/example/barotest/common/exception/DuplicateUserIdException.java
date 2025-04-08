package com.example.barotest.common.exception;

import com.example.barotest.common.response.ErrorInfo;

public class DuplicateUserIdException extends BaseException {
    public DuplicateUserIdException() {
        super(ErrorInfo.DUPLICATE_USER_ID_ERROR);
    }
}
