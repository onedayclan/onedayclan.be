package com.clanone.onedayclan.member.application.port.out;

import com.clanone.onedayclan.member.adapter.out.persistence.MemberEntity;

import java.util.Optional;

public interface GetMemberPort {
    Optional<MemberEntity> getMemberById(String id);
}
