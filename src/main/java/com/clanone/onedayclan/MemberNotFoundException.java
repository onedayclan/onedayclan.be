package com.clanone.onedayclan;

public class MemberNotFoundException extends OnedayclanException{
    private static final ErrorCode ERROR_CODE = ErrorCode.MEMBER_NOT_FOUND;

    public MemberNotFoundException(Exception e) {
        super(ERROR_CODE, ERROR_CODE.getResultMessage(), e);
    }

    public MemberNotFoundException() {
        super(ERROR_CODE, ERROR_CODE.getResultMessage());
    }
}
