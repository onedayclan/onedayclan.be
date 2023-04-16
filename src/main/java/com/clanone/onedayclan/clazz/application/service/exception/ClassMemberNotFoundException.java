package com.clanone.onedayclan.clazz.application.service.exception;

import com.clanone.onedayclan.ErrorCode;
import com.clanone.onedayclan.OnedayclanException;

public class ClassMemberNotFoundException extends OnedayclanException {
    private static final ErrorCode ERROR_CODE = ErrorCode.CLASS_MEMBER_NOT_FOUND;

    public ClassMemberNotFoundException() {
        super(ERROR_CODE, ERROR_CODE.getResultMessage());
    }
}
