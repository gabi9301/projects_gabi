package com.trip.hotel_gabriella.common.model;

import com.trip.hotel_gabriella.common.domain.ReservationRoom;
import com.trip.hotel_gabriella.common.interfaces.model.GenericResponseEntityAdapter;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.Getter;

@Getter
public class RoomReservationInfo extends BaseDTO implements GenericResponseEntityAdapter<ReservationRoom> {

    private Long reservation_id;

    private Long room_id;

    @Override
    public RoomReservationInfo fromEntity(ReservationRoom reservationRoom) {
        this.reservation_id = reservationRoom.getReservation().getId();
        this.room_id = reservationRoom.getRoom().getId();
        return this;
    }
}
