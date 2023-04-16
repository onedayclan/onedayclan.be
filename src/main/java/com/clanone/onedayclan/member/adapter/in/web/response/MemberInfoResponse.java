package com.clanone.onedayclan.member.adapter.in.web.response;

import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class MemberInfoResponse {
    private String organization;
    private String name;

    public static MemberInfoResponse of (MemberEntity memberEntity) {
        return MemberInfoResponse.builder()
                .organization(Objects.nonNull(memberEntity.getConfirmOrganization()) ?
                        memberEntity.getConfirmOrganization().getName() : "")
                .name(memberEntity.getName())
                .build();
    }
}
