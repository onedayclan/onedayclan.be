package com.clanone.onedayclan.member.application.exception;

import com.clanone.onedayclan.ErrorCode;
import com.clanone.onedayclan.OnedayclanException;

public class OrganizationNotFoundException extends OnedayclanException {
    private static final ErrorCode ERROR_CODE = ErrorCode.ORGANIZATION_NOT_FOUND;

    public OrganizationNotFoundException() {
        super(ERROR_CODE, ERROR_CODE.getResultMessage());
    }
}
