package com.trip.hotel_gabriella.user.model.search;

import com.trip.hotel_gabriella.common.domain.Reservation;
import com.trip.hotel_gabriella.common.domain.ReservationRoom;
import com.trip.hotel_gabriella.common.domain.Room;
import com.trip.hotel_gabriella.common.domain.ViewType;
import com.trip.hotel_gabriella.common.interfaces.model.GenericRequestEntityAdapter;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RoomSearchRequest extends BaseDTO implements GenericRequestEntityAdapter<ReservationRoom> {

    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private int capacity;
    private String viewType;

    @Override
    public ReservationRoom toEntity() {

        Reservation reservation = Reservation.builder()
                .checkIn(checkIn)
                .checkOut(checkOut)
                .capacity(capacity)
                        .build();

        Room room = Room.builder()
                        .viewType(ViewType.valueOf(viewType))
                        .build();

       return ReservationRoom.builder()
                .reservation(reservation)
                .room(room)
                .build();

    }
}
