package com.clanone.onedayclan.customer.adapter.in.web.response;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TermsEntity;
import com.clanone.onedayclan.customer.domain.enums.TermsType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AdminTermsResponse {
    private long seq;
    private String title;
    private TermsType type;
    private LocalDateTime createdAt;

    public static AdminTermsResponse of(TermsEntity terms) {
        return AdminTermsResponse.builder()
                .seq(terms.getSeq())
                .title(terms.getTitle())
                .type(terms.getType())
                .createdAt(terms.getCreatedAt())
                .build();
    }

    public String getType() {
        return this.type.getName();
    }
}
