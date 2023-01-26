package com.clanone.onedayclan.member.application.service;

import com.clanone.onedayclan.member.application.port.in.JoinMemberPort;
import com.clanone.onedayclan.member.application.port.out.SaveMemberPort;
import com.clanone.onedayclan.member.domain.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JoinMemberService implements JoinMemberPort {

    private final SaveMemberPort saveMemberPort;

    @Override
    public void joinMember(Member member) {
        saveMemberPort.joinMember(member);
    }
}
