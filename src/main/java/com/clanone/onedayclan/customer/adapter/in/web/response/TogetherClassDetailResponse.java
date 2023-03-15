package com.clanone.onedayclan.customer.adapter.in.web.response;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TogetherClassEntity;
import com.clanone.onedayclan.customer.domain.enums.TogetherClassCategory;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Builder
public class TogetherClassDetailResponse {
    private String title;
    private LocalDateTime createdAt;
    private TogetherClassCategory category;
    private String content;
    private String answer;
    private LocalDateTime answerCreatedAt;

    public static TogetherClassDetailResponse of(TogetherClassEntity togetherClassEntity){
        return TogetherClassDetailResponse.builder()
                .title(togetherClassEntity.getTitle())
                .createdAt(togetherClassEntity.getCreatedAt())
                .category(togetherClassEntity.getCategory())
                .content(togetherClassEntity.getContent())
                .answer(togetherClassEntity.getAnswer())
                .answerCreatedAt(togetherClassEntity.getAnswerCreatedAt())
                .build();
    }

    public String getCategory(){
        return this.category.getName();
    }
}
