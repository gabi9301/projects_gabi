package com.trip.hotel_gabriella.user.model.reservation;

import com.trip.hotel_gabriella.common.domain.ReservationRoom;
import com.trip.hotel_gabriella.common.interfaces.model.GenericResponseEntityAdapter;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.Getter;

@Getter
public class RoomReserveResponse extends BaseDTO implements GenericResponseEntityAdapter<ReservationRoom> {

    private Long id;

    @Override
    public RoomReserveResponse fromEntity(ReservationRoom reservationRoom) {
        this.id = reservationRoom.getId();
        return this;
    }
}
