package com.clanone.onedayclan.member.application.exception;

import com.clanone.onedayclan.ErrorCode;
import com.clanone.onedayclan.OnedayclanException;

public class InvalidLinkException extends OnedayclanException {
    private static final ErrorCode ERROR_CODE = ErrorCode.INVALID_LINK;

    public InvalidLinkException(String message) {
        super(ERROR_CODE, message);
    }
}
