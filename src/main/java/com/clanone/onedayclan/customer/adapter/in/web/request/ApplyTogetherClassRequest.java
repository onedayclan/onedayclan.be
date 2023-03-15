package com.clanone.onedayclan.customer.adapter.in.web.request;

import com.clanone.onedayclan.customer.domain.enums.TogetherClassCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ApplyTogetherClassRequest {
    @NotBlank(message = "제목을 입력하세요")
    private String title;

    @NotNull(message = "분류를 선택해주세요")
    private TogetherClassCategory category;

    private int limitPeople;

    @NotBlank(message = "내용을 입력해주세요")
    @Size(max = 500)
    private String content;
}
