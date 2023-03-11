package com.clanone.onedayclan.customer.domain.enums;

import lombok.Getter;

@Getter
public enum FaqCategory {
    MEMBER("회원 관련"),
    SERVICE("서비스 이용 관련");

    private String name;

    FaqCategory(String name) {
        this.name = name;
    }
}
