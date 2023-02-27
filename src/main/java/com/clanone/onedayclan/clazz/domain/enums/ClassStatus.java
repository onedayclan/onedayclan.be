package com.clanone.onedayclan.clazz.domain.enums;

import lombok.Getter;

@Getter
public enum ClassStatus {
    IN_PROGRESS("진행 중"),
    LIMIT_END("정원 마감"),
    END_BEFORE_CHECK("마감(출석 체크 전)"),
    END("마감");

    private String name;

    ClassStatus(String name) {
        this.name = name;
    }
}
