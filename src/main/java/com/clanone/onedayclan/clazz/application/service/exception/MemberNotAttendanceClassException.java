package com.clanone.onedayclan.clazz.application.service.exception;

import com.clanone.onedayclan.ErrorCode;
import com.clanone.onedayclan.OnedayclanException;

public class MemberNotAttendanceClassException extends OnedayclanException {
    private static final ErrorCode ERROR_CODE = ErrorCode.NOT_ATTENDANCE_CLASS;

    public MemberNotAttendanceClassException() {
        super(ERROR_CODE, ERROR_CODE.getResultMessage());
    }

}
