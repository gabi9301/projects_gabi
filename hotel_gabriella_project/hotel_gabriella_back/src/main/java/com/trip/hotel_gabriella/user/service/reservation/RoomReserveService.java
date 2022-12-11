package com.trip.hotel_gabriella.user.service.reservation;

import com.trip.hotel_gabriella.common.model.RoomReservationInfo;
import com.trip.hotel_gabriella.user.model.reservation.ReservationReadRequest;
import com.trip.hotel_gabriella.user.model.reservation.RoomReserveRequest;
import com.trip.hotel_gabriella.user.model.reservation.RoomReserveResponse;

import java.util.List;

public interface RoomReserveService {

    RoomReserveResponse reserveRoom(RoomReserveRequest roomReserveRequest);

    RoomReservationInfo readReservedRoom(Long reservation_id);

    List<RoomReservationInfo> readReservation(Long room_id);




}
