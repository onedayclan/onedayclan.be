package com.clanone.onedayclan.member.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PasswordResetRequest {
    @NotBlank(message = "변경할 비밀번호가 없습니다.")
    private String password;

    @NotBlank(message = "인증코드가 필요합니다.")
    private String authorizationCode;
}
