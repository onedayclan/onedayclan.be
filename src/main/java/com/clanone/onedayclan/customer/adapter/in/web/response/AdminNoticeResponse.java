package com.clanone.onedayclan.customer.adapter.in.web.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdminNoticeResponse {
    private long seq;
    private String title;
    private boolean showYn;
    private LocalDateTime createdAt;
}
