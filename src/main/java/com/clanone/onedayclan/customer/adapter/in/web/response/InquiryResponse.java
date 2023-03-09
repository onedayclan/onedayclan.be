package com.clanone.onedayclan.customer.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class InquiryResponse {
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private List<InquiryAnswerResponse> answers;

    public void updateAnswerList(List<InquiryAnswerResponse> answerList) {
        this.answers = answerList;
    }
}
