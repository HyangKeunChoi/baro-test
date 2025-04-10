package com.example.barotest.common.exception;

import com.example.barotest.common.response.CommonErrorResponse;
import com.example.barotest.common.response.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class LogicExceptionHandler {

    /*
    * http status: 500
    * 모니터링 필요
    */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<CommonErrorResponse> handleException(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new CommonErrorResponse(e.getMessage(), ErrorInfo.INTERNAL_SERVER_ERROR));
    }

    /*
     * http status: 200
     * 비즈니스 로직 처리
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<CommonErrorResponse> handleBaseException(BaseException e) {
        return ResponseEntity.status(e.getErrorInfo().getErrorCode())
                .body(new CommonErrorResponse(e.getErrorInfo().getErrorMsg(), e.getErrorInfo()));
    }

    /*
     * MethodArgumentNotValidException 에러 처리
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<CommonErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException e) {

        StringBuilder errorMessage = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach(error -> {
            errorMessage.append(error.getDefaultMessage());
        });

        log.error(errorMessage.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new CommonErrorResponse(errorMessage.toString(), ErrorInfo.INVALID_PARAMETER_ERROR));
    }
}
