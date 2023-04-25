package com.clanone.onedayclan.clazz.application.service.exception;

import com.clanone.onedayclan.ErrorCode;
import com.clanone.onedayclan.OnedayclanException;

public class ClassNotApplicationException extends OnedayclanException {
    private static final ErrorCode ERROR_CODE = ErrorCode.CLASS_NOT_APPLICATION;

    public ClassNotApplicationException() {
        super(ERROR_CODE, ERROR_CODE.getResultMessage());
    }
}
