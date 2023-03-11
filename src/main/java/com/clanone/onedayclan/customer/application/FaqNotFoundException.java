package com.clanone.onedayclan.customer.application;

import com.clanone.onedayclan.ErrorCode;
import com.clanone.onedayclan.OnedayclanException;

public class FaqNotFoundException extends OnedayclanException {
    private static final ErrorCode ERROR_CODE = ErrorCode.FAQ_NOT_FOUND;

    public FaqNotFoundException() {
        super(ERROR_CODE, ERROR_CODE.getResultMessage());
    }
}
