package com.trip.hotel_gabriella.common.exception.customException;

import lombok.Getter;

@Getter
public enum ErrorCode {

    ACCESS_TOKEN_INVALID(403, "T001", "Invalid Access Token"),
    REFRESH_TOKEN_INVALID(403, "TOO2","Invalid Refresh Token");

    private final int status;
    private final String code;
    private final String message;


    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
