package com.clanone.onedayclan.member.adapter.in.web;

import lombok.Getter;

@Getter
public class MemberJoinRequest {
    private String id;
    private String password;
    private String name;
    private String email;
    private String phone;
}
