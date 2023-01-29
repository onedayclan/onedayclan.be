package com.clanone.onedayclan.member.application.port.in;

import com.clanone.onedayclan.member.adapter.in.web.TokenResponse;

public interface LoginMemberPort {
    TokenResponse login(String id, String password);
    TokenResponse refresh(String refreshToken);
}
