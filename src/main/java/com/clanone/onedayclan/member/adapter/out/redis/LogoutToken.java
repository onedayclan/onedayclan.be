package com.clanone.onedayclan.member.adapter.out.redis;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "logoutToken", timeToLive = 60 * 30)
public class LogoutToken {
    @Id
    private String accessToken;

    public LogoutToken(final String accessToken) {
        this.accessToken = accessToken;
    }
}
