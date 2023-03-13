package com.clanone.onedayclan.customer.adapter.in.web.response;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TermsEntity;
import com.clanone.onedayclan.customer.domain.enums.TermsType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TermsResponse {
    private TermsType type;
    private String content;

    public static TermsResponse of(TermsEntity termsEntity) {
        return TermsResponse.builder()
                .type(termsEntity.getType())
                .content(termsEntity.getContent())
                .build();
    }

    public String getType() {
        return type.getName();
    }

}
