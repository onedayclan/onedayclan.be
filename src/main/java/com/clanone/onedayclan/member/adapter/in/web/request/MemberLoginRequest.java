package com.clanone.onedayclan.member.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberLoginRequest {
    @NotBlank(message = "아이디를 입력해 주세요.")
    private String id;
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;
}
