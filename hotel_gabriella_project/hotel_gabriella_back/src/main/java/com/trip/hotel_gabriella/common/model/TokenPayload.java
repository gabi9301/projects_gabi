package com.trip.hotel_gabriella.common.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenPayload {
    @NotBlank
    private String accessToken;
    @NotBlank
    private String refreshToken;

    @Builder
    public TokenPayload(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
