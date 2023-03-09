package com.clanone.onedayclan.customer.adapter.in.web.response;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.InquiryAnswerEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class InquiryAnswerResponse {
    private long seq;
    private String content;
    private LocalDateTime createdAt;

    public static InquiryAnswerResponse of(InquiryAnswerEntity inquiryAnswer) {
        return InquiryAnswerResponse.builder()
                .seq(inquiryAnswer.getSeq())
                .content(inquiryAnswer.getContent())
                .createdAt(inquiryAnswer.getCreatedAt())
                .build();
    }
}
