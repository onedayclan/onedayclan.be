package com.clanone.onedayclan.customer.adapter.in.web.response;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.FaqEntity;
import com.clanone.onedayclan.customer.domain.enums.FaqCategory;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AdminFaqResponse {
    private long seq;
    private String title;
    private FaqCategory category;
    private boolean showYn;
    private Integer orderNo;
    private LocalDateTime createdAt;

    public static AdminFaqResponse of(FaqEntity faq) {
        return AdminFaqResponse.builder()
                .seq(faq.getSeq())
                .title(faq.getTitle())
                .category(faq.getCategory())
                .showYn(faq.isShowYn())
                .createdAt(faq.getCreatedAt())
                .orderNo(faq.getOrderNo())
                .build();
    }

    public String getCategory() {
        return this.category.getName();
    }
}
