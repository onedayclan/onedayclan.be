package com.clanone.onedayclan.member.application.port.out;

import com.clanone.onedayclan.member.adapter.out.redis.RefreshToken;

public interface AuthTokenPort {
    void saveRefreshToken(String refreshToken, String userId);
    void deleteRefreshToken(RefreshToken refreshToken);
    RefreshToken getRefreshToken(String refreshToken);
}
