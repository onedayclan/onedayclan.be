package com.clanone.onedayclan.customer.adapter.in.web.response;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.NoticeEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class NoticeResponse {
    private long seq;
    private String title;
    private boolean newYn;
    private LocalDateTime createdAt;

    public static NoticeResponse of(NoticeEntity noticeEntity) {
        return NoticeResponse.builder()
                .seq(noticeEntity.getSeq())
                .title(noticeEntity.getTitle())
                .newYn(noticeEntity.getCreatedAt().plusDays(5).isAfter(LocalDateTime.now()))
                .createdAt(noticeEntity.getCreatedAt())
                .build();
    }
}
