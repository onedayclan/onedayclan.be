package com.clanone.onedayclan.clazz.adapter.in.web.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdminClassCreateRequest {
    private String name;
    private long categorySeq;
    private Integer organizationFee;
    private int normalFee;
    private String teacherName;
    private int limitPeople;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private LocalDateTime applicationEndAt;
    private boolean offlineYn;
    private String offlineLink;
    private String location;
    private long longitude;
    private long latitude;
    private long thumbnailSeq;
    private String tag;
    private String description;
    private String progress;
    private String rule;
    private boolean showYn;
}
