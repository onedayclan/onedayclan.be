package com.clanone.onedayclan.member.application.exception;

import com.clanone.onedayclan.ErrorCode;
import com.clanone.onedayclan.OnedayclanException;

public class InvalidAccessTokenException extends OnedayclanException {
    private static final ErrorCode ERROR_CODE = ErrorCode.INVALID_ACCESS_TOKEN;

    public InvalidAccessTokenException() {
        super(ERROR_CODE, ERROR_CODE.getResultMessage());
    }
}
