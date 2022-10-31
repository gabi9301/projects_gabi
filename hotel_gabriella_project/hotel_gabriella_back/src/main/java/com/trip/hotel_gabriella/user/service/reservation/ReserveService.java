package com.trip.hotel_gabriella.user.service.reservation;

import com.trip.hotel_gabriella.common.domain.ReservationType;
import com.trip.hotel_gabriella.common.model.ReservationInfo;
import com.trip.hotel_gabriella.user.model.reservation.*;

import java.util.List;

public interface ReserveService {

    ReserveResponse reserve(ReserveRequest reserveRequest);

    ReservationInfo readReservation(Long reservation_id);

    List<ReservationInfo> readReservationByType(ReservationReadRequest reservationReadRequest, ReservationType reservationType);

    List<ReservationInfo> readReservationHistory(ReservationReadRequest reservationReadRequest);

    void cancelReservation(Long reservation_id);


}
