package com.clanone.onedayclan.clazz.adapter.in.web.response;

import com.clanone.onedayclan.clazz.domain.enums.ClassStatus;
import com.clanone.onedayclan.common.application.service.utils.ImageUtil;
import jakarta.persistence.Column;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ClassDetailResponse {
    private long seq;
    private String thumbnailUrl;
    private String name;
    private String category;
    private ClassStatus status;
    private String teacherName;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private int limitPeople;
    private long applicationPeople;
    private Integer organizationFee;
    private int normalFee;
    private LocalDateTime applicationEndAt;
    private boolean offlineYn;
    private String offlineLink;
    private String description;
    private String progress;
    private double latitude;
    private double longitude;
    private String location;
    private String locationDetail;

    public String getthumbnailUrl(){
        return ImageUtil.getS3Bucket() + this.thumbnailUrl;
    }

    public String getStatus(){
        return this.status.getName();
    }
}
