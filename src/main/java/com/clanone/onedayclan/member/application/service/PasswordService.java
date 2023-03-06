package com.clanone.onedayclan.member.application.service;

import com.clanone.onedayclan.member.adapter.in.web.request.PasswordResetRequest;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.FindPasswordEntity;
import com.clanone.onedayclan.member.application.port.in.PasswordPort;
import com.clanone.onedayclan.member.application.port.out.GetMemberPort;
import com.clanone.onedayclan.member.application.port.out.UpdateMemberInfoPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordService implements PasswordPort {

    private final GetMemberPort getMemberPort;
    private final UpdateMemberInfoPort updateMemberInfoPort;

    @Override
    @Transactional
    public void resetPassword(PasswordResetRequest passwordResetRequest) {
        FindPasswordEntity memberByAuthorizationCode = getMemberPort.findMemberByAuthorizationCode(passwordResetRequest.getAuthorizationCode());
        updateMemberInfoPort.resetPassword(memberByAuthorizationCode.getEmail(),passwordResetRequest.getPassword());
        memberByAuthorizationCode.changeUsedYn();
    }
}
