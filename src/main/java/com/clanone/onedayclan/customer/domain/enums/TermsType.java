package com.clanone.onedayclan.customer.domain.enums;

import lombok.Getter;

@Getter
public enum TermsType {
    SERVICE("서비스 이용"),
    PERSONAL_INFORMATION("개인 정보"),
    FINANCIAL("전자금융거래");

    private String name;

    TermsType(String name) {
        this.name = name;
    }
}
