package com.trip.hotel_gabriella.user.service.reservation;

import com.trip.hotel_gabriella.common.model.ReservationInfo;
import com.trip.hotel_gabriella.user.model.reservation.ReserveRequest;
import com.trip.hotel_gabriella.user.model.reservation.ReserveResponse;
import com.trip.hotel_gabriella.user.model.reservation.RoomReserveRequest;
import com.trip.hotel_gabriella.user.model.reservation.RoomReserveResponse;

public interface ReserveService {

    ReserveResponse reserve(ReserveRequest reserveRequest);

    ReservationInfo readReservation(Long reservation_id);


}
