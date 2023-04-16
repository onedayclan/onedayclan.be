package com.clanone.onedayclan.clazz.adapter.in.web.request;

import lombok.Getter;

@Getter
public class AdminClassCancelMemberRequest {
    private long memberSeq;
    private String cancelReason;
}
