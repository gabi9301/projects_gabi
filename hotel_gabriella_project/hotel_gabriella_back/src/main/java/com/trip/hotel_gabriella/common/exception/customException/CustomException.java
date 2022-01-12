package com.trip.hotel_gabriella.common.exception.customException;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
