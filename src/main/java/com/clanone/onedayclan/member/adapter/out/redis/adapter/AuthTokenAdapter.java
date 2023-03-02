package com.clanone.onedayclan.member.adapter.out.redis.adapter;

import com.clanone.onedayclan.member.adapter.out.redis.model.LogoutToken;
import com.clanone.onedayclan.member.adapter.out.redis.model.RefreshToken;
import com.clanone.onedayclan.member.adapter.out.redis.repository.LogoutTokenRepository;
import com.clanone.onedayclan.member.adapter.out.redis.repository.RefreshTokenRepository;
import com.clanone.onedayclan.member.application.port.out.AuthTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthTokenAdapter implements AuthTokenPort {

    private final RefreshTokenRepository refreshTokenRepository;
    private final LogoutTokenRepository logoutTokenRepository;

    @Override
    public void saveRefreshToken(String refreshToken, String userId) {
        refreshTokenRepository.save(new RefreshToken(refreshToken, userId));
    }

    @Override
    public void deleteRefreshToken(RefreshToken refreshToken) {
        refreshTokenRepository.delete(refreshToken);
    }

    @Override
    public RefreshToken getRefreshTokenByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findById(refreshToken).orElseThrow();
    }

    @Override
    public Optional<RefreshToken> getRefreshTokenByUserId(String userId) {
        return refreshTokenRepository.findByUserId(userId);
    }

    @Override
    public void saveLogoutToken(String accessToken) {
        logoutTokenRepository.save(new LogoutToken(accessToken));
    }

    @Override
    public boolean existsLogoutToken(String accessToken) {
        return logoutTokenRepository.existsById(accessToken);
    }
}
