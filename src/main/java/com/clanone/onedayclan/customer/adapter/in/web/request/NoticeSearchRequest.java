package com.clanone.onedayclan.customer.adapter.in.web.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NoticeSearchRequest {
    private String title;
    private LocalDateTime searchStartAt;
    private LocalDateTime searchEndAt;
}
