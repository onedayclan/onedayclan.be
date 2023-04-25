package com.clanone.onedayclan.clazz.adapter.out.persistence.model;

import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassReviewSearchRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassSearchRequest;
import com.clanone.onedayclan.clazz.domain.enums.ClassStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ClassSearchModel {
    private String name;
    private LocalDateTime createdStartAt;
    private LocalDateTime createdEndAt;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private List<ClassStatus> status;
    private Boolean showYn;
    private Long categorySeq;

    public static ClassSearchModel of(AdminClassSearchRequest request) {
        return ClassSearchModel.builder()
                .name(request.getName())
                .createdStartAt(request.getCreatedStartAt())
                .createdEndAt(request.getCreatedEndAt())
                .status(request.getStatus())
                .showYn(request.getShowYn())
                .categorySeq(request.getCategorySeq())
                .build();
    }

    public static ClassSearchModel of(AdminClassReviewSearchRequest request) {
        return ClassSearchModel.builder()
                .name(request.getClassName())
                .startAt(request.getStartAt())
                .endAt(request.getEndAt())
                .categorySeq(request.getClassCategorySeq())
                .build();
    }
}
