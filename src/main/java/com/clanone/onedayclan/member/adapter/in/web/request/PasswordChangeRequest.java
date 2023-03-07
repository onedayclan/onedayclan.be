package com.clanone.onedayclan.member.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PasswordChangeRequest {

    @NotBlank(message = "기존 비밀번호를 입력해주세요.")
    private String originPassword;

    @NotBlank(message = "새로운 비밀번로를 입력해주세요.")
    private String newPassword;
}