package com.clanone.onedayclan.clazz.adapter.in.web.response;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassEntity;
import com.clanone.onedayclan.common.application.service.utils.ImageUtil;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class LatestClassResponse {
    private long seq;
    private String thumbnailUrl;
    private String name;
    private LocalDateTime startAt;
    private String description;
    private String category;
    private int organizationFee;
    private int normalFee;

    public static LatestClassResponse of(ClassEntity classEntity){
        return LatestClassResponse.builder()
                .seq(classEntity.getSeq())
                .thumbnailUrl(ImageUtil.getS3Bucket() + classEntity.getThumbnail().getUrl())
                .name(classEntity.getName())
                .startAt(classEntity.getStartAt())
                .description(classEntity.getDescription())
                .category(classEntity.getCategory().getName())
                .organizationFee(classEntity.getOrganizationFee())
                .normalFee(classEntity.getNormalFee())
                .build();
    }

}
