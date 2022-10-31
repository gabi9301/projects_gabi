package com.trip.hotel_gabriella.user.service.reservation;


import com.trip.hotel_gabriella.common.domain.ReservationRoom;
import com.trip.hotel_gabriella.common.model.RoomReservationInfo;
import com.trip.hotel_gabriella.user.model.reservation.ReservationReadRequest;
import com.trip.hotel_gabriella.user.model.reservation.RoomReserveRequest;
import com.trip.hotel_gabriella.user.model.reservation.RoomReserveResponse;
import com.trip.hotel_gabriella.user.repository.ReservationRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomReserveServiceImpl implements RoomReserveService{

    private final ReservationRoomRepository reservationRoomRepository;

    @Override
    public RoomReserveResponse reserveRoom(RoomReserveRequest roomReserveRequest) {

        ReservationRoom reservationRoom = roomReserveRequest.toEntity();

        reservationRoom = reservationRoomRepository.save(reservationRoom);

       return new RoomReserveResponse()
               .fromEntity(reservationRoom);

    }

    @Override
    public RoomReservationInfo readReservedRoom(Long reservation_id) {
       ReservationRoom reservationRoom
               = reservationRoomRepository
               .findByReservationId(reservation_id)
               .orElseThrow(NoSuchElementException::new);
        return new RoomReservationInfo().fromEntity(reservationRoom);

    }


}
