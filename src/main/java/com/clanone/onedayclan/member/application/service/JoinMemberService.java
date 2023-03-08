package com.clanone.onedayclan.member.application.service;

import com.clanone.onedayclan.member.adapter.in.web.response.EmailCheckResponse;
import com.clanone.onedayclan.member.adapter.in.web.response.OrganizationResponse;
import com.clanone.onedayclan.member.application.port.in.JoinMemberPort;
import com.clanone.onedayclan.member.application.port.out.CheckEmailPort;
import com.clanone.onedayclan.member.application.port.out.GetMemberPort;
import com.clanone.onedayclan.member.application.port.out.SaveMemberPort;
import com.clanone.onedayclan.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JoinMemberService implements JoinMemberPort {

    private final SaveMemberPort saveMemberPort;
    private final CheckEmailPort checkEmailPort;
    private final GetMemberPort getMemberPort;

    @Override
    public void joinMember(Member member) {
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
}
