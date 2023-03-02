package com.clanone.onedayclan.member.application.service;

import com.clanone.onedayclan.member.adapter.in.web.request.FindIdRequest;
import com.clanone.onedayclan.member.adapter.in.web.response.MemberFindResponse;
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
        String userId = findUserIdPort.findUserId(findIdRequest.getName(), findIdRequest.getPhone());

        return MemberFindResponse.builder()
                .email(userId)
                .build();
    }
}
