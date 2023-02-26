package com.clanone.onedayclan.member.domain.enums;

import lombok.Getter;

@Getter
public enum MemberType {
    NORMAL("일반 회원"),
    ADMIN("중앙 관리자"),
    ORGANIZATION("기업 관리자");

    private String name;

    MemberType(String name) {
        this.name = name;
    }
}
