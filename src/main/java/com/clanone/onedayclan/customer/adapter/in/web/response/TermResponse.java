package com.clanone.onedayclan.customer.adapter.in.web.response;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TermsEntity;
import com.clanone.onedayclan.customer.domain.enums.TermsType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TermResponse {
    private TermsType type;
    private String content;

    public static TermResponse of(TermsEntity termsEntity) {
        return TermResponse.builder()
                .type(termsEntity.getType())
                .content(termsEntity.getContent())
                .build();
    }

    public String getType() {
        return type.getName();
    }

}
