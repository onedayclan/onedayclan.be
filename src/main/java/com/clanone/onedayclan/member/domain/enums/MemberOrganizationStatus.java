package com.clanone.onedayclan.member.domain.enums;

import lombok.Getter;

@Getter
public enum MemberOrganizationStatus {
    ASSIGNED("승인 완료"),
    WAITING("대기 중"),
    NON_ASSIGNED("거절");

    private String name;

    MemberOrganizationStatus(String name) {
        this.name = name;
    }
}
