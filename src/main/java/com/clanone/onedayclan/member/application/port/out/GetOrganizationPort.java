package com.clanone.onedayclan.member.application.port.out;

import com.clanone.onedayclan.member.adapter.out.persistence.entity.OrganizationEntity;

public interface GetOrganizationPort {
    OrganizationEntity getOrganizationBySeq(long organizationSeq);
}
