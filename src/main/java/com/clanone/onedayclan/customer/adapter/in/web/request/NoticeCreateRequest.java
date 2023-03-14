package com.clanone.onedayclan.customer.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class NoticeCreateRequest {
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    private Long imageSeq;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    private boolean showYn;

    public boolean hasImage() {
        return this.imageSeq != null;
    }
}
