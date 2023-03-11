package com.clanone.onedayclan.customer.adapter.in.web.request;

import com.clanone.onedayclan.customer.domain.enums.FaqCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class FaqManageRequest {
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @NotNull(message = "카테고리를 선택해주세요.")
    private FaqCategory category;

    private boolean showYn;
}
