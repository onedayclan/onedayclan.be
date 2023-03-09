package com.clanone.onedayclan.customer.adapter.in.web.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class InquiryAnswerResponse {
    private long seq;
    private String content;
    private LocalDateTime createdAt;

    @QueryProjection
    public InquiryAnswerResponse(long seq, String content, LocalDateTime createdAt) {
        this.seq = seq;
        this.content = content;
        this.createdAt = createdAt;
    }

}
