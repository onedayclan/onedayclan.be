package com.clanone.onedayclan.member.adapter.in.web;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LogoutRequest {
    @NotBlank(message = "accessToken 이 필요합니다.")
    private String accessToken;
}
