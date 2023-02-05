package com.clanone.onedayclan.member.adapter.in.web;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class FindIdRequest {
    @NotBlank(message = "이름을 입력해 주세요.")
    private String name;

    @NotBlank(message = "휴대폰 번호를 입력해 주세요.")
    private String phone;
}
