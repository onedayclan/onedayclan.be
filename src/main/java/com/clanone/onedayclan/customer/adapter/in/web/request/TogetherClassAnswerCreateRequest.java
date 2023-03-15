package com.clanone.onedayclan.customer.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TogetherClassAnswerCreateRequest {
    @NotBlank(message = "답변을 입력해주세요.")
    private String answer;
}
