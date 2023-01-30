package com.clanone.onedayclan.member.application.port.out;

import com.clanone.onedayclan.member.adapter.out.redis.RefreshToken;

import java.util.Optional;

public interface AuthTokenPort {
    void saveRefreshToken(String refreshToken, String userId);
    void deleteRefreshToken(RefreshToken refreshToken);
    RefreshToken getRefreshTokenByRefreshToken(String refreshToken);
    Optional<RefreshToken> getRefreshTokenByUserId(String userId);
    void saveLogoutToken(String accessToken);
    boolean existsLogoutToken(String accessToken);
}
