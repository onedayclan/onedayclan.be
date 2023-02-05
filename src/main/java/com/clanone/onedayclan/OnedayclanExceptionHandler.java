package com.clanone.onedayclan;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<OnedayclanResponse<Void>> handleUsernameNotFoundException(AuthenticationException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(OnedayclanResponse.ofCodeMessage(HttpStatus.FORBIDDEN, "회원정보가 일치하지 않습니다.\n" + "다시 확인해 주세요."));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<OnedayclanResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(OnedayclanResponse.ofCodeMessage(HttpStatus.BAD_REQUEST, exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()));
    }
}


