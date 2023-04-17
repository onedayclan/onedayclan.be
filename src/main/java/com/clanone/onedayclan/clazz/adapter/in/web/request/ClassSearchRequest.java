package com.clanone.onedayclan.clazz.adapter.in.web.request;

import com.clanone.onedayclan.clazz.domain.enums.ClassListSort;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClassSearchRequest {
    private Long categorySeq;
    private String searchKeyword;
    private ClassListSort sort;
    private double longitude;
    private double latitude;
}
