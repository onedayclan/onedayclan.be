package com.clanone.onedayclan.member.adapter.in.web.response;

import com.clanone.onedayclan.member.domain.enums.MemberOrganizationStatus;
import com.clanone.onedayclan.member.domain.enums.MemberStatusType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

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
    private long memberCount;

    public String getOrganizationStatus() {
        if(Objects.isNull(this.organizationStatus)) {
            return null;
        }
        return this.organizationStatus.getName();
    }

    public String getStatus() {
        return this.status.getName();
    }
}
