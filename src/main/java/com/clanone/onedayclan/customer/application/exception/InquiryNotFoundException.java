package com.clanone.onedayclan.customer.application.exception;

import com.clanone.onedayclan.ErrorCode;
import com.clanone.onedayclan.OnedayclanException;

public class InquiryNotFoundException extends OnedayclanException {
    private static final ErrorCode ERROR_CODE = ErrorCode.INVALID_INQUIRY;

    public InquiryNotFoundException() {
        super(ERROR_CODE,ERROR_CODE.getResultMessage());
    }
}
