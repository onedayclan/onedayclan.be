package com.clanone.onedayclan.member.adapter.in.web.response;

import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class OrganizationMemberDetailResponse {
    private Long seq;
    private String userId;
    private String name;
    private OrganizationResponse organization;
    private String phone;
    private long memberCount;
    private LocalDateTime createdAt;
    private String memo;

    public static OrganizationMemberDetailResponse of(MemberEntity member) {
        return OrganizationMemberDetailResponse.builder()
                .seq(member.getSeq())
                .userId(member.getUserId())
                .name(member.getName())
                .organization(OrganizationResponse.of(member.getConfirmOrganization()))
                .phone(member.getPhone())
                .createdAt(member.getCreatedAt())
                .memo(member.getMemo())
                .build();
    }

    public static OrganizationMemberDetailResponse of(MemberEntity member, long memberCount) {
        return OrganizationMemberDetailResponse.builder()
                .seq(member.getSeq())
                .userId(member.getUserId())
                .name(member.getName())
                .organization(OrganizationResponse.of(member.getConfirmOrganization()))
                .phone(member.getPhone())
                .memberCount(memberCount)
                .createdAt(member.getCreatedAt())
                .memo(member.getMemo())
                .build();
    }
}
