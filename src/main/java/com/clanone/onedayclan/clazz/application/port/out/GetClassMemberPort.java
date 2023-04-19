package com.clanone.onedayclan.clazz.application.port.out;

import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;

public interface GetClassMemberPort {
    Long getClassApplicationPeople(long classSeq);
    boolean existsClassMember(long memberSeq, long classSeq);

}
