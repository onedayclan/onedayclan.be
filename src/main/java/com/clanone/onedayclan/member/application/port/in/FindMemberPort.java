package com.clanone.onedayclan.member.application.port.in;

import com.clanone.onedayclan.member.adapter.in.web.request.FindIdRequest;
import com.clanone.onedayclan.member.adapter.in.web.request.MemberSearchRequest;
import com.clanone.onedayclan.member.adapter.in.web.response.*;
import com.clanone.onedayclan.member.domain.enums.MemberStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface FindMemberPort {
    MemberFindResponse findId(FindIdRequest findIdRequest);
    Page<MemberSearchResponse> searchMemberList(MemberSearchRequest request, Pageable pageable);
    Page<MemberSearchResponse> searchOrganizationMemberList(MemberSearchRequest request, Pageable pageable);
    MemberDetailResponse getMember(long memberSeq);
    OrganizationMemberDetailResponse getOrganizationMember(long memberSeq);
    Page<OrganizationConfirmResponse> getOrganizationConfirmList(Pageable pageable);
}
