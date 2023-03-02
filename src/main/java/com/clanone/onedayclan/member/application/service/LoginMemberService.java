package com.clanone.onedayclan.member.application.service;

import com.clanone.onedayclan.member.adapter.in.auth.JwtTokenProvider;
import com.clanone.onedayclan.member.adapter.in.web.response.TokenResponse;
import com.clanone.onedayclan.member.adapter.out.redis.model.RefreshToken;
import com.clanone.onedayclan.member.application.exception.InvalidAccessTokenException;
import com.clanone.onedayclan.member.application.port.in.LoginMemberPort;
import com.clanone.onedayclan.member.application.port.out.AuthTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginMemberService implements LoginMemberPort {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthTokenPort authTokenPort;

    @Override
    public TokenResponse login(String id, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenResponse tokenInfo = jwtTokenProvider.generateToken(authentication);
        authTokenPort.saveRefreshToken(tokenInfo.getRefreshToken(), id);

        return tokenInfo;
    }

    @Override
    public TokenResponse refresh(String refreshToken) {
        RefreshToken refreshTokenInfo = authTokenPort.getRefreshTokenByRefreshToken(refreshToken);

        TokenResponse tokenInfo = jwtTokenProvider.refreshToken(refreshTokenInfo.getUserId());

        authTokenPort.saveRefreshToken(tokenInfo.getRefreshToken(), refreshTokenInfo.getUserId());
        authTokenPort.deleteRefreshToken(refreshTokenInfo);

        return tokenInfo;
    }

    @Override
    public void logout(String accessToken) {
        if(!jwtTokenProvider.validateToken(accessToken)) {
            throw new InvalidAccessTokenException();
        }
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
        authTokenPort.getRefreshTokenByUserId(authentication.getName()).ifPresent(r -> authTokenPort.deleteRefreshToken(r));
        authTokenPort.saveLogoutToken(accessToken);
    }

    @Override
    public boolean validationLogout(String accessToken) {
        return authTokenPort.existsLogoutToken(accessToken);
    }
}
