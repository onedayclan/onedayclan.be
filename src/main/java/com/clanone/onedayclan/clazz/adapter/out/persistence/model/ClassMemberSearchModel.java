package com.clanone.onedayclan.clazz.adapter.out.persistence.model;

import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassMemberSearchRequest;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ClassMemberSearchModel {
    private String className;
    private String userId;
    private Long organizationSeq;
    private Long classCategorySeq;
    private LocalDateTime createdStartAt;
    private LocalDateTime createdEndAt;

    public static ClassMemberSearchModel of(AdminClassMemberSearchRequest request) {
        return ClassMemberSearchModel.builder()
                .className(request.getClassName())
                .userId(request.getUserId())
                .organizationSeq(request.getOrganizationSeq())
                .classCategorySeq(request.getClassCategorySeq())
                .createdStartAt(request.getCreatedStartAt())
                .createdEndAt(request.getCreatedEndAt())
                .build();
    }
}
