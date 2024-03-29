package com.clanone.onedayclan.member.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SmsRequest {
    @NotBlank(message = "받을 사람의 휴대폰 번호를 입력해 주세요.")
    private String to;

    public String getTo() {
        return to.replaceAll("-","");
    }
}
