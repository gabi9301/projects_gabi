package com.trip.hotel_gabriella.user.repository;

import com.trip.hotel_gabriella.common.domain.ReservationRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRoomRepository extends JpaRepository<ReservationRoom, Long> {

    Optional<ReservationRoom> findByReservation_id(Long reservation_id);

}
