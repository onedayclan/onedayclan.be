package com.clanone.onedayclan.member.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrganizationResponse {
    private long seq;
    private String name;
}
