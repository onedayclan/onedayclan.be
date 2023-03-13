package com.clanone.onedayclan.member.application.service;

import com.clanone.onedayclan.member.adapter.in.web.request.ChangePhoneRequest;
import com.clanone.onedayclan.member.adapter.in.web.request.MemberUpdateRequest;
import com.clanone.onedayclan.member.adapter.in.web.request.OrganizationMemberUpdateRequest;
import com.clanone.onedayclan.member.adapter.in.web.response.MemberDetailResponse;
import com.clanone.onedayclan.member.adapter.in.web.response.OrganizationMemberDetailResponse;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import com.clanone.onedayclan.member.application.port.in.ManageMemberPort;
import com.clanone.onedayclan.member.application.port.out.GetMemberPort;
import com.clanone.onedayclan.member.application.port.out.GetOrganizationPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManageMemberService implements ManageMemberPort {
    private final GetMemberPort getMemberPort;
    private final GetOrganizationPort getOrganizationPort;

    @Override
    @Transactional
    public MemberDetailResponse updateNormalMember(MemberUpdateRequest request, long memberSeq) {
        MemberEntity member = getMemberPort.findMember(memberSeq);
        member.updateMemberInfo(request);

        if(member.isOrganizationChanged(request.getOrganizationSeq())) {
            member.updateOrganization(request.getOrganizationSeq() == null ?
                    null : getOrganizationPort.getOrganizationBySeq(request.getOrganizationSeq()));
        }
        return MemberDetailResponse.of(member);
    }

    @Override
    @Transactional
    public OrganizationMemberDetailResponse updateOrganizationMember(OrganizationMemberUpdateRequest request, long memberSeq) {
        MemberEntity member = getMemberPort.findMember(memberSeq);
        member.updateOrganizationMemberInfo(request);

        long memberCount = getMemberPort.countMemberByOrganizationSeq(member.getConfirmOrganization().getSeq());
        return OrganizationMemberDetailResponse.of(member, memberCount);
    }

    @Override
    @Transactional
    public void acceptOrganizationMember(long memberSeq) {
        MemberEntity member = getMemberPort.findMember(memberSeq);
        member.acceptOrganization();
    }

    @Override
    @Transactional
    public void rejectOrganizationMember(long memberSeq) {
        MemberEntity member = getMemberPort.findMember(memberSeq);
        member.rejectOrganization();
    }

    @Override
    @Transactional
    public void changePhone(String userId, ChangePhoneRequest changePhoneRequest) {
        MemberEntity memberByUserId = getMemberPort.getMemberByUserId(userId);
        memberByUserId.changePhone(changePhoneRequest.getPhone());
    }
}
