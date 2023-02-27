package com.clanone.onedayclan.customer.domain.enums;

import lombok.Getter;

@Getter
public enum TogetherClassCategory {
    MAKE_CLASS("클래스를 만들고 싶어요"),
    WANT_CLASS("클래스를 만들어주세요");

    private String name;

    TogetherClassCategory(String name) {
        this.name = name;
    }
}
