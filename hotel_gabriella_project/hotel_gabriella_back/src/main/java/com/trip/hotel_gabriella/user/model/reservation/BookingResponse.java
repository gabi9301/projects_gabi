package com.trip.hotel_gabriella.user.model.reservation;

import com.trip.hotel_gabriella.common.interfaces.model.GenericHttpResponseTransferor;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookingResponse extends BaseDTO implements GenericHttpResponseTransferor {

    private Long reserveId;

    private Long roomReserveId;

}
