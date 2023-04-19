package com.clanone.onedayclan.clazz.adapter.in.web.response;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApplyClassResponse {
    private long seq;
    private String name;
    private String teacherName;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private int limitPeople;
    private Long applicationPeople;
    private boolean offlineYn;
    private String offlineLink;
    private double latitude;
    private double longitude;
    private String location;
    private String locationDetail;

    public static ApplyClassResponse of(ClassEntity classEntity, Long applicationPeople){
        return ApplyClassResponse.builder()
                .seq(classEntity.getSeq())
                .name(classEntity.getName())
                .teacherName(classEntity.getTeacherName())
                .startAt(classEntity.getStartAt())
                .endAt(classEntity.getEndAt())
                .limitPeople(classEntity.getLimitPeople())
                .applicationPeople(applicationPeople)
                .offlineYn(classEntity.isOfflineYn())
                .offlineLink(classEntity.getOfflineLink())
                .latitude(classEntity.getLatitude())
                .longitude(classEntity.getLongitude())
                .location(classEntity.getLocation())
                .locationDetail(classEntity.getLocationDetail())
                .build();
    }
}
