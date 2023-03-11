package com.clanone.onedayclan.member.application.exception;

import com.clanone.onedayclan.ErrorCode;
import com.clanone.onedayclan.OnedayclanException;

public class AlreadyExistsUserIdException extends OnedayclanException {
    private static final ErrorCode ERROR_CODE = ErrorCode.ALREADY_EXISTS_USER_ID;

    public AlreadyExistsUserIdException() {
        super(ERROR_CODE, ERROR_CODE.getResultMessage());
    }
}
