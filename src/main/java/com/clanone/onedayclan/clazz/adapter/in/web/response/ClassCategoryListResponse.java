package com.clanone.onedayclan.clazz.adapter.in.web.response;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassCategoryEntity;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClassCategoryListResponse {
    private long seq;
    private String name;

    public static ClassCategoryListResponse of(ClassCategoryEntity classCategory) {
        return ClassCategoryListResponse.builder()
                .seq(classCategory.getSeq())
                .name(classCategory.getName())
                .build();
    }
}
