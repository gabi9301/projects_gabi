package com.trip.hotel_gabriella.common.exception.customException;

public class BookingConditionsNotMatchException extends CustomException {

    public BookingConditionsNotMatchException() {
        super(ErrorCode.BOOKING_CONDITIONS_TANGLED);
    }
}


