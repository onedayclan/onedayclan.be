package com.clanone.onedayclan;

public class MemberAlreadyDeletedException extends OnedayclanException{
    private static final ErrorCode ERROR_CODE = ErrorCode.MEMBER_ALREADY_DELETED;

    public MemberAlreadyDeletedException(Exception e) {
        super(ERROR_CODE, ERROR_CODE.getResultMessage(), e);
    }
}
