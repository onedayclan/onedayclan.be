package com.clanone.onedayclan.customer.domain.enums;

import lombok.Getter;

@Getter
public enum FaqCategory {
    MEMBER("회원"),
    SERVICE("서비스 이용");

    private String name;

    FaqCategory(String name) {
        this.name = name;
    }
}
