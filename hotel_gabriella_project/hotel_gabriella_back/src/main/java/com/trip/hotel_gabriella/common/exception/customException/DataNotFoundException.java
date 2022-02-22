package com.trip.hotel_gabriella.common.exception.customException;

public class DataNotFoundException extends CustomException {

    public DataNotFoundException() {
        super(ErrorCode.DATA_NOT_FOUND);
    }
}
