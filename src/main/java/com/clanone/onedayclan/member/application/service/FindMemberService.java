package com.clanone.onedayclan.member.application.service;

import com.clanone.onedayclan.member.adapter.in.web.request.FindIdRequest;
import com.clanone.onedayclan.member.adapter.in.web.request.FindPasswordRequest;
import com.clanone.onedayclan.member.adapter.in.web.request.MemberSearchRequest;
import com.clanone.onedayclan.member.adapter.in.web.response.*;
import com.clanone.onedayclan.member.adapter.out.email.EmailAdapter;
import com.clanone.onedayclan.member.adapter.out.model.MemberSearchModel;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import com.clanone.onedayclan.member.application.port.in.FindMemberPort;
import com.clanone.onedayclan.member.application.port.out.GetMemberPort;
import com.clanone.onedayclan.member.application.port.out.SaveMemberPort;
import com.clanone.onedayclan.member.application.service.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FindMemberService implements FindMemberPort {

    private final GetMemberPort getMemberPort;
    private final SaveMemberPort saveMemberPort;
    private final EmailAdapter emailAdapter;

    @Override
    public MemberFindResponse findId(FindIdRequest findIdRequest) {
        String userId = getMemberPort.getUserId(findIdRequest.getName(), findIdRequest.getPhone());

        return MemberFindResponse.builder()
                .email(userId)
                .build();
    }

    @Override
    public void findPassword(FindPasswordRequest findPasswordRequest) {
        MemberEntity member = getMemberPort.getMemberByUserIdAndNameAndPhone(findPasswordRequest.getUserId(), findPasswordRequest.getUserName(), findPasswordRequest.getPhone());
        String authorizationCode = NumberUtil.generateAuthorizationNumber();

        saveMemberPort.saveFindPassword(member.getUserId(), authorizationCode);

        emailAdapter.sendEmail(member.getUserId(), "비밀번호 찾기 결과입니다.", "http://www.onedayclan.com/findpassword?code="+authorizationCode);
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
    public MemberDetailResponse getMember(long memberSeq) {
        return MemberDetailResponse.of(getMemberPort.findMember(memberSeq));
    }

    @Override
    public OrganizationMemberDetailResponse getOrganizationMember(long memberSeq) {
        return getMemberPort.getOrganizationMember(memberSeq);
    }

    @Override
    public Page<OrganizationConfirmResponse> getOrganizationConfirmList(Pageable pageable) {
        return getMemberPort.getOrganizationConfirmList(pageable);
    }

    @Override
    public MemberInfoResponse getMemberInfo(String userId) {
        MemberEntity member = getMemberPort.getMemberByUserId(userId);
        return MemberInfoResponse.of(member);
    }

    @Override
    public MyPageMemberInfoResponse getMyPageMemberInformation(String userId) {
        MemberEntity member = getMemberPort.getMemberByUserId(userId);
        return MyPageMemberInfoResponse.of(member);
    }
}
