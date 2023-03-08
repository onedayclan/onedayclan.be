package com.clanone.onedayclan.member.application.port.in;

import com.clanone.onedayclan.member.adapter.in.web.request.MemberUpdateRequest;
import com.clanone.onedayclan.member.adapter.in.web.response.MemberDetailResponse;

public interface ManageMemberPort {
    MemberDetailResponse updateNormalMember(MemberUpdateRequest request, long memberSeq);
}
