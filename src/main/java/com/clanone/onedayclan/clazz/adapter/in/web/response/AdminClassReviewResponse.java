package com.clanone.onedayclan.clazz.adapter.in.web.response;

import com.clanone.onedayclan.common.application.service.utils.ImageUtil;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AdminClassReviewResponse {
    public long seq;
    private String thumbnailUrl;
    private String classCategory;
    private String className;
    private long attendanceCount;
    private long reviewCount;
    private double reviewScore;
    private LocalDateTime startAt;

    public static AdminClassReviewResponse of(AdminClassInfoResponse classInfo, AdminClassReviewInfoResponse reviewInfo) {
        return AdminClassReviewResponse.builder()
                .seq(classInfo.getSeq())
                .thumbnailUrl(ImageUtil.getS3Bucket() + classInfo.getThumbnailUrl())
                .classCategory(classInfo.getClassCategory())
                .className(classInfo.getClassName())
                .attendanceCount(classInfo.getAttendanceCount())
                .reviewCount(reviewInfo.getReviewCount())
                .reviewScore(reviewInfo.getReviewScore())
                .startAt(classInfo.getStartAt())
                .build();
    }
}
