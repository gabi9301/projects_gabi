package com.trip.hotel_gabriella.user.service.reservation;

import com.trip.hotel_gabriella.common.domain.Reservation;
import com.trip.hotel_gabriella.common.model.ReservationInfo;
import com.trip.hotel_gabriella.user.model.reservation.ReserveRequest;
import com.trip.hotel_gabriella.user.model.reservation.ReserveResponse;
import com.trip.hotel_gabriella.user.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReserveServiceImpl implements ReserveService{

    private final ReservationRepository reservationRepository;

    @Value("${hotel.time.defaultCheckInHour}")
    private String defaultCheckInHour;

    @Value("${hotel.time.defaultCheckOutHour}")
    private String defaultCheckOutHour;

    @Transactional
    public ReserveResponse reserve(ReserveRequest reserveRequest) {

        Reservation reservation = reserveRequest.toEntity();

        reservation.changeCheckIn(
                checkTimeParse(reserveRequest.getCheckIn(), defaultCheckInHour));

        reservation.changeCheckOut(
                checkTimeParse(reserveRequest.getCheckOut(), defaultCheckOutHour));

        reservation = reservationRepository.save(reservation);

        return new ReserveResponse().fromEntity(reservation);
    }

    @Override
    public ReservationInfo readReservation(Long id) {
        Reservation reservation
                = reservationRepository.findById(id).orElseThrow(NoSuchElementException::new);

        return new ReservationInfo().fromEntity(reservation);
    }

    public LocalDateTime checkTimeParse(String checkDate, String checkHour){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        return LocalDateTime.parse(checkDate + checkHour, formatter);
    }


}
