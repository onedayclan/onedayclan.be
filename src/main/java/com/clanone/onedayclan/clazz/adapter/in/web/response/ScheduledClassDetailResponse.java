package com.clanone.onedayclan.clazz.adapter.in.web.response;

import com.clanone.onedayclan.clazz.application.model.ScheduledClassDetailModel;
import com.clanone.onedayclan.clazz.domain.enums.AttendanceCheck;
import com.clanone.onedayclan.clazz.domain.enums.ScheduledClassStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Builder
public class ScheduledClassDetailResponse {
    private ScheduledClassStatus status;
    private String name;
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

    public static ScheduledClassDetailResponse of(ScheduledClassDetailModel model){

        ScheduledClassStatus status = null;

        if (!model.isCancelYn()) {
            if (model.getAttendanceCheck() == AttendanceCheck.NONE){
                status = ScheduledClassStatus.COMPLETED_APPLY;
            } else if (model.getAttendanceCheck().equals(AttendanceCheck.ATTENDANCE)
                    && Objects.isNull(model.getReviewSeq())) {
                status = ScheduledClassStatus.WAITING_REVIEW;
            } else if (model.getAttendanceCheck().equals(AttendanceCheck.ATTENDANCE)
                    && Objects.nonNull(model.getReviewSeq())) {
                status = ScheduledClassStatus.ATTENDANCE;
            } else if (model.getAttendanceCheck().equals(AttendanceCheck.ABSENT)) {
                status = ScheduledClassStatus.ABSENT;
            }
        } else {
            status = ScheduledClassStatus.CANCEL;
        }
        
        return ScheduledClassDetailResponse.builder()
                .status(status)
                .name(model.getClassName())
                .teacherName(model.getTeacherName())
                .startAt(model.getStartAt())
                .endAt(model.getEndAt())
                .limitPeople(model.getLimitPeople())
                .applicationPeople(model.getApplicationPeople())
                .offlineYn(model.isOfflineYn())
                .offlineLink(model.getOfflineLink())
                .latitude(model.getLatitude())
                .longitude(model.getLongitude())
                .location(model.getLocation())
                .locationDetail(model.getLocationDetail())
                .cancelMessage(model.getCancelMessage())
                .build();
    }
}
