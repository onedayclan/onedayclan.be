package com.clanone.onedayclan.clazz.adapter.in.web.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdminClassInfoResponse {
    private long seq;
    private String thumbnailUrl;
    private String className;
    private String classCategory;
    private int attendanceCount;
    private LocalDateTime startAt;
}
