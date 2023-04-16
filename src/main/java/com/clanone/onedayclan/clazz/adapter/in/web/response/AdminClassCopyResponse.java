package com.clanone.onedayclan.clazz.adapter.in.web.response;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassTagEntity;
import com.clanone.onedayclan.common.application.service.utils.ImageUtil;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class AdminClassCopyResponse {
    private String name;
    private long categorySeq;
    private Integer organizationFee;
    private int normalFee;
    private String teacherName;
    private int limitPeople;
    private boolean offlineYn;
    private String offlineLink;
    private String location;
    private double longitude;
    private double latitude;
    private String thumbnailUrl;
    private long thumbnailSeq;
    private String tag;
    private String description;
    private String progress;
    private String rule;
    private boolean showYn;

    public static AdminClassCopyResponse of(ClassEntity clazz, List<ClassTagEntity> tagList) {
        return AdminClassCopyResponse.builder()
                .name(clazz.getName())
                .categorySeq(clazz.getCategory().getSeq())
                .organizationFee(clazz.getOrganizationFee())
                .normalFee(clazz.getNormalFee())
                .teacherName(clazz.getTeacherName())
                .limitPeople(clazz.getLimitPeople())
                .offlineYn(clazz.isOfflineYn())
                .offlineLink(clazz.getOfflineLink())
                .location(clazz.getLocation())
                .longitude(clazz.getLongitude())
                .latitude(clazz.getLatitude())
                .thumbnailUrl(ImageUtil.getS3Bucket() + clazz.getThumbnail().getUrl())
                .thumbnailSeq(clazz.getThumbnail().getSeq())
                .tag(tagList.stream().map(ClassTagEntity::getName).collect(Collectors.joining(",")))
                .description(clazz.getDescription())
                .progress(clazz.getProgress())
                .rule(clazz.getRule())
                .showYn(clazz.isShowYn())
                .build();
    }
}
