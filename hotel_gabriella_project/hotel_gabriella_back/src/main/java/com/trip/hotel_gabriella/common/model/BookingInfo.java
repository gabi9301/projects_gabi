package com.trip.hotel_gabriella.common.model;


import com.trip.hotel_gabriella.common.interfaces.model.GenericHttpResponseTransferor;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookingInfo extends BaseDTO implements GenericHttpResponseTransferor {

    private ReservationInfo reservationInfo;

    private RoomInfo roomInfo;


}
