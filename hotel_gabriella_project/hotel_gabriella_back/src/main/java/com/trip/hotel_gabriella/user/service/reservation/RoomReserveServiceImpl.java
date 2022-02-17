package com.trip.hotel_gabriella.user.service.reservation;


import com.trip.hotel_gabriella.user.model.reservation.RoomReserveRequest;
import com.trip.hotel_gabriella.user.model.reservation.RoomReserveResponse;
import com.trip.hotel_gabriella.user.repository.ReservationRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomReserveServiceImpl implements RoomReserveService{

    private final ReservationRoomRepository reservationRoomRepository;

    @Override
    public RoomReserveResponse bookRoom(RoomReserveRequest roomReserveRequest) {
        return null;
    }
}
