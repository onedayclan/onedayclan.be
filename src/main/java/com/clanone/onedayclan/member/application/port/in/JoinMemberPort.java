package com.clanone.onedayclan.member.application.port.in;

import com.clanone.onedayclan.member.adapter.in.web.response.EmailCheckResponse;
import com.clanone.onedayclan.member.adapter.in.web.response.OrganizationResponse;
import com.clanone.onedayclan.member.domain.Member;

import java.util.List;

public interface JoinMemberPort {
    void joinMember(Member member);
    EmailCheckResponse checkAvailableEmail(String email);
    List<OrganizationResponse> getOrganizationList();
}
