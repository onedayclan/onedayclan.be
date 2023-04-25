package com.clanone.onedayclan.clazz.adapter.in.web.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AdminClassReviewSearchRequest {
    private String className;
    private Long classCategorySeq;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
}
