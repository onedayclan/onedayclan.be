package com.clanone.onedayclan.clazz.adapter.in.web.response;

import com.clanone.onedayclan.clazz.application.model.ScheduledClassModel;
import com.clanone.onedayclan.clazz.domain.enums.AttendanceCheck;
import com.clanone.onedayclan.clazz.domain.enums.ScheduledClassStatus;
import com.clanone.onedayclan.common.application.service.utils.ImageUtil;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Builder
public class ScheduledClassResponse {
    private long classSeq;
    private String thumbnailUrl;
    private ScheduledClassStatus status;
    private LocalDateTime penaltyStartAt;
    private LocalDateTime penaltyEndAt;
    private String name;
    private LocalDateTime startAt;
    private String category;

    public static ScheduledClassResponse of(ScheduledClassModel scheduledClassModel){

        ScheduledClassStatus status = ScheduledClassStatus.COMPLETED_APPLY;

        if (!scheduledClassModel.isCancelYn()) {
            if (scheduledClassModel.getAttendanceCheck() == AttendanceCheck.NONE){
                status = ScheduledClassStatus.COMPLETED_APPLY;
            } else if (scheduledClassModel.getAttendanceCheck().equals(AttendanceCheck.ATTENDANCE)
                    && Objects.isNull(scheduledClassModel.getReviewSeq())) {
                status = ScheduledClassStatus.WAITING_REVIEW;
            } else if (scheduledClassModel.getAttendanceCheck().equals(AttendanceCheck.ATTENDANCE)
                    && Objects.nonNull(scheduledClassModel.getReviewSeq())) {
                status = ScheduledClassStatus.ATTENDANCE;
            } else if (scheduledClassModel.getAttendanceCheck().equals(AttendanceCheck.ABSENT)) {
                status = ScheduledClassStatus.ABSENT;
            }
        } else {
            status = ScheduledClassStatus.CANCEL;
        }

        return ScheduledClassResponse.builder()
                .classSeq(scheduledClassModel.getClassSeq())
                .thumbnailUrl(ImageUtil.getS3Bucket() + scheduledClassModel.getThumbnailUrl())
                .status(status)
                .penaltyStartAt(scheduledClassModel.getPenaltyStartAt())
                .penaltyEndAt(scheduledClassModel.getPenaltyEndAt())
                .name(scheduledClassModel.getClassName())
                .startAt(scheduledClassModel.getClassStartAt())
                .category(scheduledClassModel.getCategory())
                .build();
    }
}
