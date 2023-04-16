package com.clanone.onedayclan.clazz.adapter.in.web.response;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassTagEntity;
import com.clanone.onedayclan.clazz.domain.enums.ClassStatus;
import com.clanone.onedayclan.common.application.service.utils.ImageUtil;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class AdminClassDetailResponse {
    private String name;
    private ClassStatus status;
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
    private double longitude;
    private double latitude;
    private String thumbnailUrl;
    private String tag;
    private String description;
    private String progress;
    private String rule;
    private boolean showYn;

    public static AdminClassDetailResponse of(ClassEntity clazz, List<ClassTagEntity> tagList) {
        return AdminClassDetailResponse.builder()
                .name(clazz.getName())
                .status(clazz.getStatus())
                .categorySeq(clazz.getCategory().getSeq())
                .organizationFee(clazz.getOrganizationFee())
                .normalFee(clazz.getNormalFee())
                .teacherName(clazz.getTeacherName())
                .limitPeople(clazz.getLimitPeople())
                .startAt(clazz.getStartAt())
                .endAt(clazz.getEndAt())
                .applicationEndAt(clazz.getApplicationEndAt())
                .offlineYn(clazz.isOfflineYn())
                .offlineLink(clazz.getOfflineLink())
                .location(clazz.getLocation())
                .longitude(clazz.getLongitude())
                .latitude(clazz.getLatitude())
                .thumbnailUrl(ImageUtil.getS3Bucket() + clazz.getThumbnail().getUrl())
                .tag(tagList.stream().map(ClassTagEntity::getName).collect(Collectors.joining(",")))
                .description(clazz.getDescription())
                .progress(clazz.getProgress())
                .rule(clazz.getRule())
                .showYn(clazz.isShowYn())
                .build();
    }
}
