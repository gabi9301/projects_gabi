package com.trip.hotel_gabriella.common.domain;

import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@RedisHash(value = "user", timeToLive = 30)
public class RedisUser {

    @Id
    private String id;
    private final String account;
    private final String refreshToken;

    public RedisUser(String account, String refreshToken) {
        this.account = account;
        this.refreshToken = refreshToken;
    }
}
