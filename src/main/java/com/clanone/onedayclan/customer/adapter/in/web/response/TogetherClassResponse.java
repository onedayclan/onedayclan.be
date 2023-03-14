package com.clanone.onedayclan.customer.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TogetherClassResponse {
    private long seq;
    private String title;
    private String answerYn;
    private LocalDateTime createdAt;
}
