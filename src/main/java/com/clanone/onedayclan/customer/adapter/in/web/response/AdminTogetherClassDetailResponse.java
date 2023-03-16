package com.clanone.onedayclan.customer.adapter.in.web.response;

import com.clanone.onedayclan.customer.domain.enums.TogetherClassCategory;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdminTogetherClassDetailResponse {
    private long seq;
    private String name;
    private String userId;
    private String title;
    private String organizationName;
    private TogetherClassCategory category;
    private String content;
    private String answer;
    private LocalDateTime createdAt;

    public String getCategory() {
        return this.category.getName();
    }
}
