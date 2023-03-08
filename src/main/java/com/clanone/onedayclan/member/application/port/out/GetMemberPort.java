package com.clanone.onedayclan.member.application.port.out;

import com.clanone.onedayclan.member.adapter.in.web.response.MemberDetailResponse;
import com.clanone.onedayclan.member.adapter.in.web.response.MemberSearchResponse;
import com.clanone.onedayclan.member.adapter.in.web.response.OrganizationResponse;
import com.clanone.onedayclan.member.adapter.out.model.MemberSearchModel;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.FindPasswordEntity;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GetMemberPort {
    Optional<MemberEntity> getMemberById(String id);
    FindPasswordEntity findMemberByAuthorizationCode(String authorizationCode);
    Page<MemberSearchResponse> searchMemberList(MemberSearchModel searchModel, Pageable pageable);
    Page<MemberSearchResponse> searchOrganizationMemberList(MemberSearchModel searchModel, Pageable pageable);
    MemberDetailResponse findMember(long memberSeq);
    List<OrganizationResponse> getOrganizationList();
}
