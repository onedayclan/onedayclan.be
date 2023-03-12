package com.clanone.onedayclan.customer.adapter.in.web.response;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.FaqEntity;
import com.clanone.onedayclan.customer.domain.enums.FaqCategory;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminFaqDetailResponse {
    private long seq;
    private String title;
    private String content;
    private FaqCategory category;
    private boolean showYn;
    private Integer orderNo;

    public static AdminFaqDetailResponse of(FaqEntity faq) {
        return AdminFaqDetailResponse.builder()
                .seq(faq.getSeq())
                .title(faq.getTitle())
                .content(faq.getContent())
                .category(faq.getCategory())
                .showYn(faq.isShowYn())
                .orderNo(faq.getOrderNo())
                .build();
    }

    public String getCategory(){
        return this.category.getName();
    }
}
