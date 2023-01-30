package com.clanone.onedayclan;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, 1001, "회원 정보가 존재하지 않습니다."),
    MEMBER_ALREADY_DELETED(HttpStatus.BAD_REQUEST, 1002, "삭제할 수 없는 회원입니다."),
    INVALID_ACCESS_TOKEN(HttpStatus.BAD_REQUEST, 2001, "유효하지 않은 액세스 토큰입니다.");

    private final HttpStatus httpStatus;
    private final Integer resultCode;
    private final String resultMessage;

    ErrorCode(HttpStatus httpStatus, Integer resultCode, String resultMessage){
        this.httpStatus = httpStatus;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }
}
