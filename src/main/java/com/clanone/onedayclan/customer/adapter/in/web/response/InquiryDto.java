package com.clanone.onedayclan.customer.adapter.in.web.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class InquiryDto {
    private String title;
    private String content;
    private boolean answerYn;
    private LocalDateTime createdAt;

    @QueryProjection
    public InquiryDto(String title, String content, boolean answerYn, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.answerYn = answerYn;
        this.createdAt = createdAt;
    }
}
