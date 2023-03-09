package com.clanone.onedayclan.member.application.port.out;

import com.clanone.onedayclan.member.adapter.out.persistence.entity.OrganizationEntity;

public interface SaveOrganizationPort {
    OrganizationEntity saveOrganization(OrganizationEntity organization);
}
