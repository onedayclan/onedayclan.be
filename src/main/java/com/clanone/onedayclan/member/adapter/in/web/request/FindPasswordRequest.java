package com.clanone.onedayclan.member.adapter.in.web.request;

import lombok.Getter;

@Getter
public class FindPasswordRequest {
    private String userId;
    private String userName;
    private String phone;
}
