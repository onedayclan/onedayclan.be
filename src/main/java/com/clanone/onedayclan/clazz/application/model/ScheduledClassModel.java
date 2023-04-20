package com.clanone.onedayclan.clazz.application.model;

import com.clanone.onedayclan.clazz.domain.enums.AttendanceCheck;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduledClassModel {
    private long classSeq;
    private String thumbnailUrl;
    private boolean cancelYn;
    private AttendanceCheck attendanceCheck;
    private Long reviewSeq;
    private String className;
    private LocalDateTime classStartAt;
    private String category;
}
