package com.clanone.onedayclan.customer.adapter.out.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class InquirySearchModel {
    private String userId;
    private String name;
    private LocalDateTime searchStartAt;
    private LocalDateTime searchEndAt;
    private Boolean answerYn;
    private Boolean deleteYn;
}
