package com.clanone.onedayclan.customer.adapter.in.web.response;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TogetherClassEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Builder
public class TogetherClassResponse {
    private long seq;
    private String title;
    private String answerYn;
    private LocalDateTime createdAt;

    public static TogetherClassResponse of(TogetherClassEntity togetherClassEntity) {
        return TogetherClassResponse.builder()
                .seq(togetherClassEntity.getSeq())
                .title(togetherClassEntity.getTitle())
                .createdAt(togetherClassEntity.getCreatedAt())
                .answerYn(Objects.isNull(togetherClassEntity.getAnswer()) ? "미답변" : "답변완료")
                .build();
    }
}
