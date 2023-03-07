package com.clanone.onedayclan.member.domain.enums;

import lombok.Getter;

@Getter
public enum MemberStatusType {
    NORMAL("이용 중"),
    WITHDRAW("탈퇴"),
    STOP("정지"),
    DORMANT("휴면");

    private String name;

    MemberStatusType(String name) {
        this.name = name;
    }
}
