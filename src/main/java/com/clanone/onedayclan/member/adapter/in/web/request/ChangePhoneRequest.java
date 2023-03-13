package com.clanone.onedayclan.member.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ChangePhoneRequest {

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    private String phone;
}
