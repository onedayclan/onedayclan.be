package com.clanone.onedayclan.member.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyPageMemberInfoResponse {
    private String userId;
    private String name;
    private String organizationName;
    private String organizationStatus;
    private String phone;
}
