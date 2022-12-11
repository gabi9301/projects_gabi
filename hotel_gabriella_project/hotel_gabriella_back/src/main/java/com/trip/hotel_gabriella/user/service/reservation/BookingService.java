package com.trip.hotel_gabriella.user.service.reservation;

import com.trip.hotel_gabriella.common.model.BookingInfo;
import com.trip.hotel_gabriella.common.model.RoomReservationInfo;
import com.trip.hotel_gabriella.user.model.reservation.BookingCommand;
import com.trip.hotel_gabriella.user.model.reservation.BookingResponse;
import com.trip.hotel_gabriella.user.model.reservation.ReservationReadRequest;

import java.util.List;

public interface BookingService {

    BookingResponse bookReservation(BookingCommand bookingCommand);

    BookingInfo bookSearch(Long reservation_Id);

    void bookCancel(Long reservation_Id);

    List<BookingInfo> bookSearchHistory(ReservationReadRequest reservationReadRequest);

}
