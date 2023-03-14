package com.clanone.onedayclan.customer.adapter.out.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NoticeSearchModel {
    private String title;
    private LocalDateTime searchStartAt;
    private LocalDateTime searchEndAt;
}
