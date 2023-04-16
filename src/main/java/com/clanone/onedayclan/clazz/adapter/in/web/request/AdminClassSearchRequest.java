package com.clanone.onedayclan.clazz.adapter.in.web.request;

import com.clanone.onedayclan.clazz.domain.enums.ClassStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class AdminClassSearchRequest {
    private String name;
    private LocalDateTime createdStartAt;
    private LocalDateTime createdEndAt;
    private List<ClassStatus> status;
    private Boolean showYn;
    private Long categorySeq;
}
