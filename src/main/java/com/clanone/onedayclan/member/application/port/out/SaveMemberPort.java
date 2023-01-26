package com.clanone.onedayclan.member.application.port.out;

import com.clanone.onedayclan.member.domain.Member;

public interface SaveMemberPort {
    void joinMember(Member member);
}
