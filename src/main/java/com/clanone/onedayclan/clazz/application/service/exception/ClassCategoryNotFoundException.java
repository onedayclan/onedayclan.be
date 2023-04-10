package com.clanone.onedayclan.clazz.application.service.exception;

import com.clanone.onedayclan.ErrorCode;
import com.clanone.onedayclan.OnedayclanException;

public class ClassCategoryNotFoundException extends OnedayclanException {
    private static final ErrorCode ERROR_CODE = ErrorCode.CLASS_CATEGORY_NOT_FOUND;

    public ClassCategoryNotFoundException() {
        super(ERROR_CODE, ERROR_CODE.getResultMessage());
    }
}
