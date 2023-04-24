package com.clanone.onedayclan.clazz.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class ScheduledClassInfoResponse {
    private long attendanceCount;
    private LocalDateTime penaltyStartAt;
    private LocalDateTime penaltyEndAt;
    private String displayMessage;
    private List<CancelClassMessageResponse> cancelMessageByClass = new ArrayList<>();
}
