package com.trip.hotel_gabriella.common.exception.customException;


public class AccessTokenInvalidException extends CustomException {

    public AccessTokenInvalidException() {
        super(ErrorCode.ACCESS_TOKEN_INVALID);
    }
}
