package com.clanone.onedayclan;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,"일치하는 회원 정보가 없습니다."),
    MEMBER_ALREADY_DELETED(HttpStatus.BAD_REQUEST,"삭제할 수 없는 회원입니다."),
    INVALID_ACCESS_TOKEN(HttpStatus.BAD_REQUEST,"유효하지 않은 액세스 토큰입니다."),
    INVALID_LINK(HttpStatus.BAD_REQUEST,"유효하지 않은 링크입니다."),
    ORGANIZATION_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 기관번호입니다."),
    INVALID_INQUIRY(HttpStatus.NOT_FOUND, "존재하지 않는 문의입니다."),
    INVALID_POSTING_MEMBER(HttpStatus.BAD_REQUEST, "작성자 정보가 일치하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String resultMessage;

    ErrorCode(HttpStatus httpStatus, String resultMessage){
        this.httpStatus = httpStatus;
        this.resultMessage = resultMessage;
    }
}
