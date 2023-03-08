package com.clanone.onedayclan.member.adapter.out.persistence.adapter;

import com.clanone.onedayclan.member.adapter.out.persistence.entity.OrganizationEntity;
import com.clanone.onedayclan.member.adapter.out.persistence.repository.OrganizationEntityRepository;
import com.clanone.onedayclan.member.application.exception.OrganizationNotFoundException;
import com.clanone.onedayclan.member.application.port.out.GetOrganizationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrganizationAdapter implements GetOrganizationPort {

    private final OrganizationEntityRepository organizationEntityRepository;

    @Override
    public OrganizationEntity getOrganizationBySeq(long organizationSeq) {
        return organizationEntityRepository.findById(organizationSeq).orElseThrow(() -> {throw new OrganizationNotFoundException();});
    }
}
