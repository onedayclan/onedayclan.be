package com.clanone.onedayclan.member.application.port.in;

import com.clanone.onedayclan.member.adapter.in.web.request.SmsConfirmRequest;
import com.clanone.onedayclan.member.adapter.in.web.response.SmsConfirmResponse;

public interface AuthorizationNumberPort {
    void sendAuthorizationNumber(String to);
    SmsConfirmResponse confirmAuthorizationNumber(SmsConfirmRequest smsConfirmRequest);
}
