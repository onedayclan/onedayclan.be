package com.clanone.onedayclan.common.application.service.exception;

import com.clanone.onedayclan.ErrorCode;
import com.clanone.onedayclan.OnedayclanException;

public class ImageNotFoundException extends OnedayclanException {
    private static final ErrorCode ERROR_CODE = ErrorCode.IMAGE_NOT_FOUND;

    public ImageNotFoundException() {
        super(ERROR_CODE, ERROR_CODE.getResultMessage());
    }
}
