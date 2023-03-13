package com.clanone.onedayclan.customer.domain.enums;

import lombok.Getter;

@Getter
public enum TermsType {
    SERVICE("서비스 이용 약관"),
    PERSONAL_INFORMATION("개인정보 취급 방침"),
    FINANCIAL("전자금융거래 이용약관");

    private String name;

    TermsType(String name) {
        this.name = name;
    }
}
