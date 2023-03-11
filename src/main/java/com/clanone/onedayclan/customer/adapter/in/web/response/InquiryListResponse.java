package com.clanone.onedayclan.customer.adapter.in.web.response;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.InquiryEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class InquiryListResponse {
    private long seq;
    private String title;
    private boolean answerYn;
    private LocalDateTime createdAt;

    public static InquiryListResponse of(InquiryEntity inquiry){
        return InquiryListResponse.builder()
                .seq(inquiry.getSeq())
                .title(inquiry.getTitle())
                .answerYn(inquiry.isAnswerYn())
                .createdAt(inquiry.getCreatedAt())
                .build();
    }
}
