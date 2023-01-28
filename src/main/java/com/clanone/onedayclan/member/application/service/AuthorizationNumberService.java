package com.clanone.onedayclan.member.application.service;

import com.clanone.onedayclan.member.adapter.in.web.SmsConfirmRequest;
import com.clanone.onedayclan.member.adapter.in.web.SmsConfirmResponse;
import com.clanone.onedayclan.member.adapter.out.persistence.AuthorizationNumberEntity;
import com.clanone.onedayclan.member.application.port.in.AuthorizationNumberPort;
import com.clanone.onedayclan.member.application.port.out.ConfirmAuthorizationNumberPort;
import com.clanone.onedayclan.member.application.port.out.SendAuthorizationNumberPort;
import com.clanone.onedayclan.member.application.port.out.SendSmsPort;
import com.clanone.onedayclan.member.application.service.util.NumberUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorizationNumberService implements AuthorizationNumberPort {

    private final SendSmsPort sendSmsPort;
    private final ConfirmAuthorizationNumberPort confirmAuthorizationNumberPort;
    private final SendAuthorizationNumberPort sendAuthorizationNumberPort;

    public void sendAuthorizationNumber(String to) {
        String authorizationNumber = NumberUtil.generateAuthorizationNumber();

        String result = sendSmsPort.sendSMS(authorizationNumber, to);

        // 디비에 휴대폰 번호 + 인증번호 저장 + 보낸 시간 + 유효시간
        final int VALID_LIMIT_MIN = 5;
        LocalDateTime validAt = LocalDateTime.now().plusMinutes(VALID_LIMIT_MIN);
        System.out.println(validAt);

        sendAuthorizationNumberPort.saveAuthorizationNumber(to,authorizationNumber,validAt);
    }

    @Transactional
    public SmsConfirmResponse confirmAuthorizationNumber(SmsConfirmRequest smsConfirmRequest) {
        AuthorizationNumberEntity authorizationNumber = confirmAuthorizationNumberPort.getAuthorizationNumber(smsConfirmRequest.getPhoneNumber(), smsConfirmRequest.getAuthorizationNumber(), LocalDateTime.now());

        if (Objects.nonNull(authorizationNumber)) {
            authorizationNumber.use();
        }
        return SmsConfirmResponse.builder().confirmYn(Objects.nonNull(authorizationNumber)).build();
    }
}
