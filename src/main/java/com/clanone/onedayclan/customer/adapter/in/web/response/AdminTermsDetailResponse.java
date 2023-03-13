package com.clanone.onedayclan.customer.adapter.in.web.response;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TermsEntity;
import com.clanone.onedayclan.customer.domain.enums.TermsType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminTermsDetailResponse {
    private long seq;
    private String title;
    private String content;
    private TermsType type;

    public static AdminTermsDetailResponse of(TermsEntity termsEntity) {
        return AdminTermsDetailResponse.builder()
                .seq(termsEntity.getSeq())
                .title(termsEntity.getTitle())
                .content(termsEntity.getContent())
                .type(termsEntity.getType())
                .build();
    }
}
