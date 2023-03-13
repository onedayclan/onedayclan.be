package com.clanone.onedayclan.customer.adapter.in.web.response;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.InquiryEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AdminInquiryDetailResponse {
    private String name;
    private String userId;
    private String title;
    private String answer;
    private LocalDateTime createdAt;

    public static AdminInquiryDetailResponse of(InquiryEntity inquiry, String answer) {
        return AdminInquiryDetailResponse.builder()
                .name(inquiry.getMember().getName())
                .userId(inquiry.getMember().getUserId())
                .title(inquiry.getTitle())
                .answer(answer)
                .createdAt(inquiry.getCreatedAt())
                .build();
    }
}
