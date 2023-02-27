package com.clanone.onedayclan.clazz.domain.enums;

import lombok.Getter;

@Getter
public enum AttendanceCheck {
    NONE("미지정"),
    ATTENDANCE("출석"),
    ABSENT("결석");

    private String name;

    AttendanceCheck(String name) {
        this.name = name;
    }
}
