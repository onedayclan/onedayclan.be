package com.clanone.onedayclan.customer.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NoticeDetailResponse {
    private String title;
    private LocalDateTime createdAt;
    private String imageUrl;
    private String content;
}
