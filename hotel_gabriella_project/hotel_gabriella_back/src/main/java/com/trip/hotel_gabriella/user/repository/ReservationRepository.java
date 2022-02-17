package com.trip.hotel_gabriella.user.repository;

import com.trip.hotel_gabriella.common.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByName(String name);

}
