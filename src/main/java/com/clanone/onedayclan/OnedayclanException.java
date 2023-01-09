package com.clanone.onedayclan;

import lombok.Getter;

@Getter
public class OnedayclanException extends RuntimeException{
    private final ErrorCode errorCode;

    public OnedayclanException(ErrorCode errorCode, String message, Exception e){
        super(message, e);
        this.errorCode = errorCode;
    }
}
