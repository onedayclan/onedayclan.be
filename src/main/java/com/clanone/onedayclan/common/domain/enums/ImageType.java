package com.clanone.onedayclan.common.domain.enums;

import lombok.Getter;

@Getter
public enum ImageType {
    CLASS_REVIEW("클래스 리뷰"),
    CLASS_POSTER("클래스 포스터"),
    CLASS_THUMBNAIL("클래스 썸네일"),
    NOTICE("공지사항 이미지");

    private String name;

    ImageType(String name) {
        this.name = name;
    }

}
