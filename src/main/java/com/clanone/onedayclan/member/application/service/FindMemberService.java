package com.clanone.onedayclan.member.application.service;

import com.clanone.onedayclan.member.adapter.in.web.FindIdRequest;
import com.clanone.onedayclan.member.adapter.in.web.MemberFindResponse;
import com.clanone.onedayclan.member.application.port.in.FindMemberPort;
import com.clanone.onedayclan.member.application.port.out.FindUserIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindMemberService implements FindMemberPort {

    private final FindUserIdPort findUserIdPort;

    @Override
    public MemberFindResponse findId(FindIdRequest findIdRequest) {
        String name = findIdRequest.getName();
        String phone = findIdRequest.getPhone();
        String userId = findUserIdPort.findUserId(name, phone);

        return MemberFindResponse.builder()
                .email(userId)
                .build();
    }
}
