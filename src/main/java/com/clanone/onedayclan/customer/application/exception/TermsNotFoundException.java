package com.clanone.onedayclan.customer.application.exception;

import com.clanone.onedayclan.ErrorCode;
import com.clanone.onedayclan.OnedayclanException;

public class TermsNotFoundException extends OnedayclanException {
    private static final ErrorCode ERROR_CODE = ErrorCode.TERMS_NOT_FOUND;

    public TermsNotFoundException() {
        super(ERROR_CODE, ERROR_CODE.getResultMessage());
    }
}
