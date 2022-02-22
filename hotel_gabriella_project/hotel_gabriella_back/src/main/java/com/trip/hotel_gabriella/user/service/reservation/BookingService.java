package com.trip.hotel_gabriella.user.service.reservation;

import com.trip.hotel_gabriella.common.model.BookingInfo;
import com.trip.hotel_gabriella.user.model.reservation.BookingCommand;
import com.trip.hotel_gabriella.user.model.reservation.BookingResponse;

public interface BookingService {

    BookingResponse bookReservation(BookingCommand bookingCommand);

    BookingInfo bookSearch(Long roomReservationId);







}
