package com.clanone.onedayclan.clazz.application.model;

import com.clanone.onedayclan.clazz.domain.enums.AttendanceCheck;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduledClassDetailModel {
    private AttendanceCheck attendanceCheck;
    private Long reviewSeq;
    private boolean cancelYn;
    private String className;
    private String teacherName;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private int limitPeople;
    private long applicationPeople;
    private boolean offlineYn;
    private String offlineLink;
    private double latitude;
    private double longitude;
    private String location;
    private String locationDetail;
    private String cancelMessage;
}
