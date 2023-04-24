package com.clanone.onedayclan.clazz.adapter.in.web.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdminClassReviewResponse {
    public long seq;
    private String thumbnailUrl;
    private String classCategory;
    private String className;
    private int attendanceCount;
    private int reviewCount;
    private float reviewScore;
    private LocalDateTime startAt;
}
