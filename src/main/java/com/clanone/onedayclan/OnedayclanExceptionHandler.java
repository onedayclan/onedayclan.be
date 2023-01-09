package com.clanone.onedayclan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class OnedayclanExceptionHandler {
    @ExceptionHandler(value = {OnedayclanException.class})
    public ResponseEntity<OnedayclanResponse<Void>> handleOnedayclanException(OnedayclanException exception){
        ErrorCode errorCode = exception.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(OnedayclanResponse.ofCodeMessage(errorCode, exception.getMessage()));
    }
}
