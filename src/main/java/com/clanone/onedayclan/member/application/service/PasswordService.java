package com.clanone.onedayclan.member.application.service;

import com.clanone.onedayclan.member.adapter.in.web.request.PasswordChangeRequest;
import com.clanone.onedayclan.member.adapter.in.web.request.PasswordResetRequest;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.FindPasswordEntity;
import com.clanone.onedayclan.member.application.exception.InvalidAccessException;
import com.clanone.onedayclan.member.application.port.in.PasswordPort;
import com.clanone.onedayclan.member.application.port.out.CheckMemberInfoPort;
import com.clanone.onedayclan.member.application.port.out.GetMemberPort;
import com.clanone.onedayclan.member.application.port.out.UpdateMemberInfoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PasswordService implements PasswordPort {

    private final GetMemberPort getMemberPort;
    private final UpdateMemberInfoPort updateMemberInfoPort;
    private final CheckMemberInfoPort checkMemberInfoPort;

    @Override
    public void resetPassword(PasswordResetRequest passwordResetRequest) {
        FindPasswordEntity memberByAuthorizationCode = getMemberPort.findMemberByAuthorizationCode(passwordResetRequest.getAuthorizationCode());

        updateMemberInfoPort.resetPassword(memberByAuthorizationCode.getEmail(),passwordResetRequest.getPassword());
        memberByAuthorizationCode.changeUsedYn();
    }

    @Override
    public void changePassword(PasswordChangeRequest passwordChangeRequest, String userId) {
        if (!checkMemberInfoPort.checkPassword(userId, passwordChangeRequest.getOriginPassword())){
            throw new InvalidAccessException("기존 비밀번호와 동일하지 않습니다.");
        }
        updateMemberInfoPort.resetPassword(userId, passwordChangeRequest.getNewPassword());
    }
}
