package com.clanone.onedayclan.member.domain.enums;

import lombok.Getter;

@Getter
public enum MemberOrganizationStatus {
    ASSIGNED("소속"),
    WAITING("승인 대기"),
    NON_ASSIGNED("미소속");

    private String name;

    MemberOrganizationStatus(String name) {
        this.name = name;
    }
}
