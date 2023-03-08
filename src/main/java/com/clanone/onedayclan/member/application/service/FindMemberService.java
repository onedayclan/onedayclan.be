package com.clanone.onedayclan.member.application.service;

import com.clanone.onedayclan.member.adapter.in.web.request.FindIdRequest;
import com.clanone.onedayclan.member.adapter.in.web.request.MemberSearchRequest;
import com.clanone.onedayclan.member.adapter.in.web.response.MemberDetailResponse;
import com.clanone.onedayclan.member.adapter.in.web.response.MemberFindResponse;
import com.clanone.onedayclan.member.adapter.in.web.response.MemberSearchResponse;
import com.clanone.onedayclan.member.adapter.out.model.MemberSearchModel;
import com.clanone.onedayclan.member.application.port.in.FindMemberPort;
import com.clanone.onedayclan.member.application.port.out.FindUserIdPort;
import com.clanone.onedayclan.member.application.port.out.GetMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindMemberService implements FindMemberPort {

    private final FindUserIdPort findUserIdPort;
    private final GetMemberPort getMemberPort;

    @Override
    public MemberFindResponse findId(FindIdRequest findIdRequest) {
        String userId = findUserIdPort.findUserId(findIdRequest.getName(), findIdRequest.getPhone());

        return MemberFindResponse.builder()
                .email(userId)
                .build();
    }

    @Override
    public Page<MemberSearchResponse> searchMemberList(MemberSearchRequest request, Pageable pageable) {
        return getMemberPort.searchMemberList(MemberSearchModel.builder()
                        .userId(request.getUserId())
                        .name(request.getName())
                        .status(request.getStatus())
                        .searchStartAt(request.getSearchStartAt())
                        .searchEndAt(request.getSearchEndAt())
                        .organizationSeq(request.getOrganizationSeq())
                        .build(), pageable);
    }

    @Override
    public Page<MemberSearchResponse> searchOrganizationMemberList(MemberSearchRequest request, Pageable pageable) {
        return getMemberPort.searchOrganizationMemberList(MemberSearchModel.builder()
                        .userId(request.getUserId())
                        .name(request.getName())
                        .status(request.getStatus())
                        .searchStartAt(request.getSearchStartAt())
                        .searchEndAt(request.getSearchEndAt())
                        .build(), pageable);
    }

    @Override
    public MemberDetailResponse findMember(long memberSeq) {
        return MemberDetailResponse.of(getMemberPort.findMember(memberSeq));
    }
}
