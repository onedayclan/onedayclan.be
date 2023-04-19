package com.clanone.onedayclan.clazz.application.service.exception;

import com.clanone.onedayclan.ErrorCode;
import com.clanone.onedayclan.OnedayclanException;

public class ClassAlreadyApplyException extends OnedayclanException {
    private static final ErrorCode ERROR_CODE = ErrorCode.CLASS_ALREADY_APPLY;

    public ClassAlreadyApplyException() {
        super(ERROR_CODE, ERROR_CODE.getResultMessage());
    }
}
