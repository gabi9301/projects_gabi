package com.trip.hotel_gabriella.user.repository;

import com.trip.hotel_gabriella.common.domain.Reservation;
import com.trip.hotel_gabriella.user.model.reservation.ReserveRequest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
@Slf4j
class ReservationRepositoryTest {

    @Autowired
    ReservationRepository repository;


    @Test
    public void insertReservationTest() throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

        //given
        Reservation reservation1 = Reservation.builder()
                .name("이민아")
                .phone("01092984423")
                .checkIn(LocalDateTime.parse("202202181400",formatter))
                .checkOut(        LocalDateTime.parse("202202231100",formatter))
                .capacity(3)
                .isMember(false)
                .build();

        Reservation reservation2 = Reservation.builder()
                .name("이민아")
                .phone("01092984423")
                .checkIn(LocalDateTime.parse("202205181400",formatter))
                .checkOut(        LocalDateTime.parse("202205231100",formatter))
                .capacity(4)
                .isMember(false)
                .build();


        //when
        repository.save(reservation1);
        repository.save(reservation2);
        repository.flush();

        List<Reservation> findReservation = repository.findByNameAndPhone("이민아", "01092984423");

        for(Reservation reservation : findReservation){
            log.debug("reservation 정원 : {} ",reservation.getCapacity());
        }

        //then

        assertThat(findReservation.size()).isEqualTo(2);
    }
    @Test
    public void findByIdTest(){

    }

}