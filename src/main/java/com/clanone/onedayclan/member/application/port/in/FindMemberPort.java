package com.clanone.onedayclan.member.application.port.in;

import com.clanone.onedayclan.member.adapter.in.web.request.FindIdRequest;
import com.clanone.onedayclan.member.adapter.in.web.response.MemberFindResponse;

public interface FindMemberPort {
    MemberFindResponse findId(FindIdRequest findIdRequest);
}
