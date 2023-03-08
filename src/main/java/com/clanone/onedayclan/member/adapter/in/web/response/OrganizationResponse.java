package com.clanone.onedayclan.member.adapter.in.web.response;

import com.clanone.onedayclan.member.adapter.out.persistence.entity.OrganizationEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrganizationResponse {
    private long seq;
    private String name;

    public static OrganizationResponse of(OrganizationEntity organization) {
        return OrganizationResponse.builder()
                .seq(organization.getSeq())
                .name(organization.getName())
                .build();
    }

}
