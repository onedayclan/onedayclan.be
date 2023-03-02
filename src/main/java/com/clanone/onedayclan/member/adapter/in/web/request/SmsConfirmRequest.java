package com.clanone.onedayclan.member.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SmsConfirmRequest {
    @NotBlank(message = "휴대폰 번호를 입력해 주세요.")
    private String phoneNumber;
    @NotBlank(message = "인증번호를 입력해 주세요.")
    private String authorizationNumber;

}
