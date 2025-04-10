package com.example.barotest.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorInfo {
    INTERNAL_SERVER_ERROR(500, "일시적인 오류가 발생했습니다. 잠심 후 다시 시도해 주세요."),
    PASSWORD_LENGTH_ERROR(501, "패스워드 길이는 12자리 이상이어야 합니다."),
    DUPLICATE_USER_ID_ERROR(502, "동일한 ID가 존재 합니다."),
    USER_NOT_FOUND_ERROR(503, "유저를 찾을 수 없습니다."),
    INVALID_PARAMETER_ERROR(504, "파라미터가 올바르지 않습니다."),
    DELIVERY_ADDRESS_UPDATE_ERROR(505, "배송지 주소를 수정할 수 없습니다."),
    DELIVERY_NOT_FOUND_ERROR(506, "배송지 주소를 찾을 수 없습니다."),
    ;

    private final int errorCode;
    private final String errorMsg;

    public String getErrorMsg(Object... arg) {
        return String.format(errorMsg, arg);
    }
}
