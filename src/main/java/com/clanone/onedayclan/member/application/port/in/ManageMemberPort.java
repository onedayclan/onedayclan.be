package com.clanone.onedayclan.member.application.port.in;

import com.clanone.onedayclan.member.adapter.in.web.request.ChangePhoneRequest;
import com.clanone.onedayclan.member.adapter.in.web.request.MemberUpdateRequest;
import com.clanone.onedayclan.member.adapter.in.web.request.OrganizationMemberUpdateRequest;
import com.clanone.onedayclan.member.adapter.in.web.response.MemberDetailResponse;
import com.clanone.onedayclan.member.adapter.in.web.response.OrganizationMemberDetailResponse;

public interface ManageMemberPort {
    MemberDetailResponse updateNormalMember(MemberUpdateRequest request, long memberSeq);
    OrganizationMemberDetailResponse updateOrganizationMember(OrganizationMemberUpdateRequest request, long memberSeq);
    void acceptOrganizationMember(long memberSeq);
    void rejectOrganizationMember(long memberSeq);
    void changePhone(String userId, ChangePhoneRequest changePhoneRequest);
}
