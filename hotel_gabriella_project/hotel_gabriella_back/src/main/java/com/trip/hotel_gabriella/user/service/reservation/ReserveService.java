package com.trip.hotel_gabriella.user.service.reservation;

import com.trip.hotel_gabriella.user.model.reservation.ReserveRequest;
import com.trip.hotel_gabriella.user.model.reservation.ReserveResponse;

public interface ReserveService {

    ReserveResponse reserve(ReserveRequest reserveRequest);

}
