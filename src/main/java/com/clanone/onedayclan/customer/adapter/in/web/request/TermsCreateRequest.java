package com.clanone.onedayclan.customer.adapter.in.web.request;

import com.clanone.onedayclan.customer.domain.enums.TermsType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TermsCreateRequest {
    @NotNull(message = "카테고리를 입력해주세요")
    private TermsType type;

    @NotEmpty(message = "내용을 입력해주세요")
    private String content;

    private String title;
}
