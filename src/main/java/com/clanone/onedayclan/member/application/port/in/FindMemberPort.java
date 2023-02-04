package com.clanone.onedayclan.member.application.port.in;

import com.clanone.onedayclan.member.adapter.in.web.FindIdRequest;
import com.clanone.onedayclan.member.adapter.in.web.MemberFindResponse;

public interface FindMemberPort {
    MemberFindResponse findId(FindIdRequest findIdRequest);
}
