package com.clanone.onedayclan.clazz.adapter.in.web.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AdminClassMemberSearchRequest {
    private String className;
    private String userId;
    private Long organizationSeq;
    private Long classCategorySeq;
    private LocalDateTime createdStartAt;
    private LocalDateTime createdEndAt;
}
