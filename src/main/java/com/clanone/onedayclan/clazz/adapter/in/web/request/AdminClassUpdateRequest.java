package com.clanone.onedayclan.clazz.adapter.in.web.request;

import lombok.Getter;

@Getter
public class AdminClassUpdateRequest {
    private String name;
    private long categorySeq;
    private Integer organizationFee;
    private int normalFee;
    private String teacherName;
    private int limitPeople;
    private String startAt;
    private String endAt;
    private String applicationEndAt;
    private boolean offlineYn;
    private String offlineLink;
    private String location;
    private String locationDetail;
    private double longitude;
    private double latitude;
    private Long thumbnailSeq;
    private String tag;
    private String description;
    private String progress;
    private String rule;
    private boolean showYn;
}
