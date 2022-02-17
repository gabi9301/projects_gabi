package com.trip.hotel_gabriella.user.service.reservation;

import com.trip.hotel_gabriella.user.model.reservation.ReserveRequest;
import com.trip.hotel_gabriella.user.model.reservation.ReserveResponse;
import com.trip.hotel_gabriella.user.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReserveServiceImpl implements ReserveService{

    private final ReservationRepository reservationRepository;

    @Transactional
    public ReserveResponse reserve(ReserveRequest reserveRequest) {

        return null;
    }

    public LocalDateTime checkInParse(String checkInDate, String checkInHour){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        return LocalDateTime.parse(checkInDate + checkInHour, formatter);
    }

    public LocalDateTime checkOutParse(String checkOutDate, String checkOutHour){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        return LocalDateTime.parse(checkOutDate + checkOutHour, formatter);
    }
}
