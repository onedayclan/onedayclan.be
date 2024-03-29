package com.clanone.onedayclan;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,"일치하는 회원 정보가 없습니다."),
    ALREADY_EXISTS_USER_ID(HttpStatus.BAD_REQUEST, "이미 존재하는 ID 입니다."),
    MEMBER_ALREADY_DELETED(HttpStatus.BAD_REQUEST,"삭제할 수 없는 회원입니다."),
    INVALID_ACCESS_TOKEN(HttpStatus.BAD_REQUEST,"유효하지 않은 액세스 토큰입니다."),
    INVALID_LINK(HttpStatus.BAD_REQUEST,"유효하지 않은 링크입니다."),
    ORGANIZATION_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 기관번호입니다."),
    FAQ_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 FAQ 입니다."),
    INVALID_INQUIRY(HttpStatus.NOT_FOUND, "존재하지 않는 문의입니다."),
    INVALID_POSTING_MEMBER(HttpStatus.BAD_REQUEST, "작성자 정보가 일치하지 않습니다."),
    TERMS_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 약관입니다."),
    INVALID_NOTICE(HttpStatus.NOT_FOUND, "존재하지 않는 공지사항입니다."),
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 이미지입니다."),
    INVALID_TOGETHER_CLASS(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다."),
    CLASS_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 카테고리입니다."),
    CLASS_INFO_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 클래스입니다."),
    CLASS_MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "신청하지 않은 회원입니다."),
    CLASS_NOT_APPLICATION(HttpStatus.BAD_REQUEST, "신청할 수 없는 클래스입니다."),
    CLASS_ALREADY_APPLY(HttpStatus.BAD_REQUEST, "이미 신청한 수업입니다."),
    NOT_ATTENDANCE_CLASS(HttpStatus.NOT_FOUND, "수업에 참석하지 않아 리뷰를 남길 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String resultMessage;

    ErrorCode(HttpStatus httpStatus, String resultMessage){
        this.httpStatus = httpStatus;
        this.resultMessage = resultMessage;
    }
}
