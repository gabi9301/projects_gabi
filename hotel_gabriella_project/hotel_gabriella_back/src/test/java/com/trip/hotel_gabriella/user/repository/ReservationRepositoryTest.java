package com.trip.hotel_gabriella.user.repository;

import com.trip.hotel_gabriella.common.domain.Reservation;
import com.trip.hotel_gabriella.user.model.reservation.ReserveRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class ReservationRepositoryTest {

    @Autowired
    ReservationRepository repository;


    @Test
    public void insertReservationTest() throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

        //given
        Reservation reservation = Reservation.builder()
                .name("이민아")
                .phone("01092984423")
                .checkIn(LocalDateTime.parse("202202181400",formatter))
                .checkOut(        LocalDateTime.parse("202202231100",formatter))
                .capacity(3)
                .isMember(false)
                .build();




        //when
        Reservation reservation1 = repository.save(reservation);
        repository.flush();

        Reservation findReservation = repository.findByName("이민아")
                .orElseThrow(NoSuchElementException::new);

        //then

        assertThat(findReservation.getCapacity()).isEqualTo(3);
        assertThat(findReservation.getPhone()).isEqualTo("01092984423");
        assertThat(findReservation.getIsMember()).isFalse();


    }



}