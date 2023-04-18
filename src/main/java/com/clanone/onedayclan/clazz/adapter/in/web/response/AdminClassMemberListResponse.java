package com.clanone.onedayclan.clazz.adapter.in.web.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdminClassMemberListResponse {
    private long seq;
    private String className;
    private String userId;
    private String phone;
    private String userName;
    private String classCategory;
    private String organizationName;
    private LocalDateTime createdAt;
}
