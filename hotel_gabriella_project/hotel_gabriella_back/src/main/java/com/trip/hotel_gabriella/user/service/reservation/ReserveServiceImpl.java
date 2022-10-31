package com.trip.hotel_gabriella.user.service.reservation;

import com.trip.hotel_gabriella.common.domain.Reservation;
import com.trip.hotel_gabriella.common.domain.ReservationType;
import com.trip.hotel_gabriella.common.model.ReservationInfo;
import com.trip.hotel_gabriella.user.model.reservation.ReservationReadRequest;
import com.trip.hotel_gabriella.user.model.reservation.ReserveRequest;
import com.trip.hotel_gabriella.user.model.reservation.ReserveResponse;
import com.trip.hotel_gabriella.user.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
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

    @Override
    public List<ReservationInfo> readReservationByType(ReservationReadRequest reservationReadRequest, ReservationType reservationType) {
        List<ReservationInfo> reservationInfos = new ArrayList<>();
        List<Reservation> readRoomReservation = reservationRepository
                .findByNameAndPhoneAndReservationType(reservationReadRequest.getName()
                        , reservationReadRequest.getPhone()
                        , reservationType);
        for(Reservation reservation : readRoomReservation){
            reservationInfos.add(new ReservationInfo().fromEntity(reservation));
        }
        return reservationInfos;


    }

    @Override
    public List<ReservationInfo> readReservationHistory(ReservationReadRequest reservationReadRequest) {
        List<ReservationInfo> reservationInfos = new ArrayList<>();
        List<Reservation> reservationHistory
                = reservationRepository.findByNameAndPhone(reservationReadRequest.getName(), reservationReadRequest.getPhone());
        for(Reservation reservation : reservationHistory){
            reservationInfos.add(new ReservationInfo().fromEntity(reservation));
        }

        return reservationInfos;
    }

    @Transactional
    public void cancelReservation(Long reservation_id) {
        Reservation reservation
                = reservationRepository.findById(reservation_id).orElseThrow(NoSuchElementException::new);

        reservation.markAsCanceled();

    }

    public LocalDateTime checkTimeParse(String checkDate, String checkHour){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        return LocalDateTime.parse(checkDate + checkHour, formatter);
    }


}
