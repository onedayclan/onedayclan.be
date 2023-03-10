package com.clanone.onedayclan.member.application.service;

import com.clanone.onedayclan.member.adapter.in.web.request.OrganizationCreateRequest;
import com.clanone.onedayclan.member.adapter.in.web.response.EmailCheckResponse;
import com.clanone.onedayclan.member.adapter.in.web.response.OrganizationMemberDetailResponse;
import com.clanone.onedayclan.member.adapter.in.web.response.OrganizationResponse;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.OrganizationEntity;
import com.clanone.onedayclan.member.application.exception.AlreadyExistsUserIdException;
import com.clanone.onedayclan.member.application.port.in.JoinMemberPort;
import com.clanone.onedayclan.member.application.port.out.CheckEmailPort;
import com.clanone.onedayclan.member.application.port.out.GetMemberPort;
import com.clanone.onedayclan.member.application.port.out.SaveMemberPort;
import com.clanone.onedayclan.member.application.port.out.SaveOrganizationPort;
import com.clanone.onedayclan.member.domain.Member;
import com.clanone.onedayclan.member.domain.enums.MemberOrganizationStatus;
import com.clanone.onedayclan.member.domain.enums.MemberType;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JoinMemberService implements JoinMemberPort {

    private final SaveMemberPort saveMemberPort;
    private final CheckEmailPort checkEmailPort;
    private final GetMemberPort getMemberPort;
    private final SaveOrganizationPort saveOrganizationPort;

    @Override
    public void joinMember(Member member) {
        if(checkEmailPort.existsEmail(member.getId())) {
            throw new AlreadyExistsUserIdException();
        }
        saveMemberPort.joinMember(member);
    }

    @Override
    public EmailCheckResponse checkAvailableEmail(String email) {
        return EmailCheckResponse.builder().available(!checkEmailPort.existsEmail(email)).build();
    }

    @Override
    public List<OrganizationResponse> getOrganizationList() {
        return getMemberPort.getOrganizationList();
    }

    @Override
    public OrganizationMemberDetailResponse insertOrganizationMember(OrganizationCreateRequest request) {
        OrganizationEntity organization = OrganizationEntity.builder()
                                                            .name(request.getOrganizationName())
                                                            .build();
        organization = saveOrganizationPort.saveOrganization(organization);

        MemberEntity member = MemberEntity.builder()
                                            .userId(request.getUserId())
                                            .name(request.getName())
                                            .phone(request.getPhone())
                                            .confirmOrganization(organization)
                                            .memo(request.getMemo())
                                            .organizationStatus(MemberOrganizationStatus.ASSIGNED)
                                            .type(MemberType.ORGANIZATION)
                                            .password(request.getPassword())
                                            .build();
        member = saveMemberPort.saveMember(member);
        return OrganizationMemberDetailResponse.of(member);
    }
}
