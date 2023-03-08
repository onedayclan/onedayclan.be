package com.clanone.onedayclan.member.adapter.in.web.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class OrganizationCreateRequest {
    @NotBlank(message = "아이디를 입력해주세요.")
    private String userId;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "기관명을 입력해주세요.")
    private String organizationName;

    @NotBlank(message = "연락처를 입력해주세요.")
    private String phone;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    private String memo;
}
