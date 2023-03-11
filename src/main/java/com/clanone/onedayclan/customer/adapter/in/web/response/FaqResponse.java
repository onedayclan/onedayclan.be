package com.clanone.onedayclan.customer.adapter.in.web.response;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.FaqEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FaqResponse {
    private String title;
    private String content;

    public static FaqResponse of(FaqEntity faqEntity) {
        return FaqResponse.builder()
                .title(faqEntity.getTitle())
                .content(faqEntity.getContent())
                .build();
    }
}
