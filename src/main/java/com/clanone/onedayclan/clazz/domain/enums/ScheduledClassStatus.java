package com.clanone.onedayclan.clazz.domain.enums;

import lombok.Getter;

@Getter
public enum ScheduledClassStatus {

    COMPLETED_APPLY("신청 완료"),
    WAITING_REVIEW("후기 작성 대기"),
    CANCEL("취소"),
    ATTENDANCE("참여 완료"),
    ABSENT("결석");

    private String name;

    ScheduledClassStatus(String name) {
        this.name = name;
    }
}
