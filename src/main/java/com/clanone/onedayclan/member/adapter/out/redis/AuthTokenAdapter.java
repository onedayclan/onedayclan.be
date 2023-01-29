package com.clanone.onedayclan.member.adapter.out.redis;

import com.clanone.onedayclan.member.application.port.out.AuthTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthTokenAdapter implements AuthTokenPort {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void saveRefreshToken(String refreshToken, String userId) {
        refreshTokenRepository.save(new RefreshToken(refreshToken, userId));
    }

    @Override
    public void deleteRefreshToken(RefreshToken refreshToken) {
        refreshTokenRepository.delete(refreshToken);
    }

    @Override
    public RefreshToken getRefreshToken(String refreshToken) {
        return refreshTokenRepository.findById(refreshToken).orElseThrow();
    }

}
