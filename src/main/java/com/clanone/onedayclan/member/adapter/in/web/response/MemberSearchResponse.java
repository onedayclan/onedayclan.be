package com.clanone.onedayclan.member.adapter.in.web.response;

import com.clanone.onedayclan.member.domain.enums.MemberOrganizationStatus;
import com.clanone.onedayclan.member.domain.enums.MemberStatusType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberSearchResponse {
    private long seq;
    private String userId;
    private String name;
    private String organization;
    private MemberOrganizationStatus organizationStatus;
    private LocalDateTime createdAt;
    private MemberStatusType status;
    private String memo;
}
