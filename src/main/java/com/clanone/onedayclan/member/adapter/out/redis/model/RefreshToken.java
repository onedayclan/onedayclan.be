package com.clanone.onedayclan.member.adapter.out.redis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 14)
public class RefreshToken {

    @Id
    private String refreshToken;
    private String userId;

    public RefreshToken(final String refreshToken, final String userId) {
        this.refreshToken = refreshToken;
        this.userId = userId;
    }

    public String getRefreshToken(){
        return refreshToken;
    }

    public String getUserId() {
        return userId;
    }
}
