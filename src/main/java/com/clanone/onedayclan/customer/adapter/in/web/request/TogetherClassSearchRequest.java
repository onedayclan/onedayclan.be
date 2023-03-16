package com.clanone.onedayclan.customer.adapter.in.web.request;

import com.clanone.onedayclan.customer.domain.enums.TogetherClassCategory;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TogetherClassSearchRequest {
    private String name;
    private String userId;
    private TogetherClassCategory category;
    private Boolean answerYn;
    private Long organizationSeq;
    private LocalDateTime searchStartAt;
    private LocalDateTime searchEndAt;
}
