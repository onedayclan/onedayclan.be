package com.clanone.onedayclan.member.application.port.in;

import com.clanone.onedayclan.member.adapter.in.web.request.FindIdRequest;
import com.clanone.onedayclan.member.adapter.in.web.request.MemberSearchRequest;
import com.clanone.onedayclan.member.adapter.in.web.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindMemberPort {
    MemberFindResponse findId(FindIdRequest findIdRequest);
    Page<MemberSearchResponse> searchMemberList(MemberSearchRequest request, Pageable pageable);
    Page<MemberSearchResponse> searchOrganizationMemberList(MemberSearchRequest request, Pageable pageable);
    MemberDetailResponse getMember(long memberSeq);
    OrganizationMemberDetailResponse getOrganizationMember(long memberSeq);
    Page<OrganizationConfirmResponse> getOrganizationConfirmList(Pageable pageable);
    MemberInfoResponse getMemberInfo(String userId);
    MyPageMemberInfoResponse getMyPageMemberInformation(String userId);
}
