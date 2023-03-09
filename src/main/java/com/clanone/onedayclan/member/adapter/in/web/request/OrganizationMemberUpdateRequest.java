package com.clanone.onedayclan.member.adapter.in.web.request;

import com.clanone.onedayclan.member.domain.enums.MemberStatusType;
import lombok.Getter;

@Getter
public class OrganizationMemberUpdateRequest {
    private String organizationName;
    private String name;
    private String phone;
    private MemberStatusType status;
    private String memo;
}
