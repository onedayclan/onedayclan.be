package com.clanone.onedayclan.member.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class RefreshTokenRequest {
    @NotBlank(message = "refreshToken이 필요합니다.")
    private String refreshToken;
}
