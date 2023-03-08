package com.clanone.onedayclan.member.application.service;

import com.clanone.onedayclan.member.adapter.in.web.request.MemberUpdateRequest;
import com.clanone.onedayclan.member.adapter.in.web.response.MemberDetailResponse;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import com.clanone.onedayclan.member.application.port.in.ManageMemberPort;
import com.clanone.onedayclan.member.application.port.out.GetMemberPort;
import com.clanone.onedayclan.member.application.port.out.GetOrganizationPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
