package com.clanone.onedayclan.member.adapter.in.web.response;

import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrganizationMemberDetailResponse {
    private Long seq;
    private String userId;
    private String name;
    private OrganizationResponse organization;
    private String phone;
    private long memberCount;

    public static OrganizationMemberDetailResponse of(MemberEntity member) {
        return OrganizationMemberDetailResponse.builder()
                .seq(member.getSeq())
                .userId(member.getUserId())
                .name(member.getName())
                .organization(OrganizationResponse.of(member.getConfirmOrganization()))
                .phone(member.getPhone())
                .build();
    }
}
