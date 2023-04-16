package com.clanone.onedayclan.member.adapter.in.web.response;

import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class MyPageMemberInfoResponse {
    private String userId;
    private String name;
    private String organizationName;
    private String organizationStatus;
    private String phone;

    public static MyPageMemberInfoResponse of (MemberEntity memberEntity){
        return MyPageMemberInfoResponse.builder()
                .name(memberEntity.getName())
                .userId(memberEntity.getUserId())
                .organizationStatus(memberEntity.getOrganizationStatus().getName())
                .organizationName(Objects.nonNull(memberEntity.getConfirmOrganization()) ?
                        memberEntity.getConfirmOrganization().getName() : "")
                .phone(memberEntity.getPhone())
                .build();
    }
}
