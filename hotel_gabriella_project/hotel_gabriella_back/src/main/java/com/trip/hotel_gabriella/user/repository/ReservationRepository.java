package com.trip.hotel_gabriella.user.repository;

import com.trip.hotel_gabriella.common.domain.Reservation;
import com.trip.hotel_gabriella.common.domain.ReservationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findByName(String name);

    List<Reservation> findByNameAndPhone(String name, String phone);

    List<Reservation> findByNameAndPhoneAndReservationType(String name, String phone, ReservationType reservationType);

    Optional<Reservation> findByIdAndReservationType(Long id, ReservationType reservationType);

//    @Query("SELECT r FROM Reservation r WHERE r.isMember= true AND r.memberId = :member_id")
//    List<Reservation> findReservationsByMember (Long member_id);

    //working on

}
