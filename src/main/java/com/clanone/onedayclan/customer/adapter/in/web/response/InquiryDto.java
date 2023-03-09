package com.clanone.onedayclan.customer.adapter.in.web.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class InquiryDto {
    private String title;
    private String content;
    private boolean answerYn;
    private LocalDateTime createdAt;
}
