package com.clanone.onedayclan.customer.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdminInquiryResponse {
    private long seq;
    private String userId;
    private String name;
    private String title;
    private boolean answerYn;
    private boolean deleteYn;
    private LocalDateTime createdAt;

    public String getAnswerYn(){
        return this.answerYn ? "답변완료" : "미답변";
    }
}
