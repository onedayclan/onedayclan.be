package com.clanone.onedayclan.member.application.service;

import com.clanone.onedayclan.member.application.port.in.SendAuthorizationNumberPort;
import com.clanone.onedayclan.member.application.port.out.SendSmsPort;
import com.clanone.onedayclan.member.application.service.util.NumberUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class SendAuthorizationNumberService implements SendAuthorizationNumberPort {

    private final SendSmsPort sendSmsPort;

    public void sendAuthorizationNumber(String to) {
        String authorizationNumber = NumberUtil.generateAuthorizationNumber();

        sendSmsPort.sendSMS(authorizationNumber, to);
    }
}
