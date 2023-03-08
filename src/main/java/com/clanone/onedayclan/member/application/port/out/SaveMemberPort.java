package com.clanone.onedayclan.member.application.port.out;

import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import com.clanone.onedayclan.member.domain.Member;

public interface SaveMemberPort {
    void joinMember(Member member);
    MemberEntity saveMember(MemberEntity member);
}
