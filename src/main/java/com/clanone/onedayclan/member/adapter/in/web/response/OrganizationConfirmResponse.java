package com.clanone.onedayclan.member.adapter.in.web.response;

import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class OrganizationConfirmResponse {
    private long seq;
    private String userId;
    private String name;
    private String phone;
    private String organizationName;
    private LocalDateTime createdAt;

    public static OrganizationConfirmResponse of(MemberEntity member) {
        return OrganizationConfirmResponse.builder()
                .seq(member.getSeq())
                .userId(member.getUserId())
                .name(member.getName())
                .organizationName(member.getRequestOrganization().getName())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
