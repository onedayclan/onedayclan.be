package com.clanone.onedayclan.customer.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostInquiryRequest {

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
}
