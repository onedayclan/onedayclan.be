package com.clanone.onedayclan.customer.adapter.in.web.response;

import com.clanone.onedayclan.customer.domain.enums.TogetherClassCategory;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdminTogetherClassResponse {
    private long seq;
    private String userId;
    private String name;
    private String title;
    private TogetherClassCategory category;
    private boolean answerYn;
    private String organizationName;
    private LocalDateTime createdAt;
    private int limitPeople;

    public String getCategory() {
        return this.category.getName();
    }

    public String getAnswerYn() {
        return this.answerYn ? "답변완료" : "미답변";
    }
}
