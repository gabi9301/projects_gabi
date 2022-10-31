package com.trip.hotel_gabriella.user.controller;

import com.trip.hotel_gabriella.common.model.BookingInfo;
import com.trip.hotel_gabriella.user.model.reservation.BookingCommand;
import com.trip.hotel_gabriella.user.model.reservation.ReservationReadRequest;
import com.trip.hotel_gabriella.user.service.reservation.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestBookController {

    private final BookingService bookingService;

    @PostMapping("/book.do")
    public ResponseEntity<Void> bookReservation(@RequestBody @Valid BookingCommand bookingCommand){

        bookingService.bookReservation(bookingCommand);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/searchReservationHistory")
    public ResponseEntity<List<BookingInfo>> bookHistory(@RequestBody @Valid ReservationReadRequest reservationReadRequest){
        List<BookingInfo> bookingInfos = bookingService.BookSearchHistory(reservationReadRequest);
        return new ResponseEntity<>(bookingInfos, HttpStatus.OK);
    }



}
