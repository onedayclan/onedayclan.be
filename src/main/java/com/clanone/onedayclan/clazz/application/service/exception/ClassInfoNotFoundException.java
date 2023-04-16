package com.clanone.onedayclan.clazz.application.service.exception;

import com.clanone.onedayclan.ErrorCode;
import com.clanone.onedayclan.OnedayclanException;

public class ClassInfoNotFoundException extends OnedayclanException {
    private static final ErrorCode ERROR_CODE = ErrorCode.CLASS_INFO_NOT_FOUND;

    public ClassInfoNotFoundException() {
        super(ERROR_CODE, ERROR_CODE.getResultMessage());
    }
}
