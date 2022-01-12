package com.trip.hotel_gabriella.common.exception.customException;

public class RefreshTokenInvalidException extends CustomException {

    public RefreshTokenInvalidException() {
        super(ErrorCode.REFRESH_TOKEN_INVALID);
    }
}
