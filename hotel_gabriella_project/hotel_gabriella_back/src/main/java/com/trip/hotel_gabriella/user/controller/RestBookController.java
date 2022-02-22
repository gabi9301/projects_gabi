package com.trip.hotel_gabriella.user.controller;

import com.trip.hotel_gabriella.user.model.reservation.BookingCommand;
import com.trip.hotel_gabriella.user.service.reservation.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RestBookController {

    private final BookingService bookingService;

    @PostMapping("/book.do")
    public ResponseEntity<Void> bookReservation(@RequestBody @Valid BookingCommand bookingCommand){

        bookingService.bookReservation(bookingCommand);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
