package com.trip.hotel_gabriella.user.repository;

import com.trip.hotel_gabriella.admin.repository.RoomRepository;
import com.trip.hotel_gabriella.common.domain.Reservation;
import com.trip.hotel_gabriella.common.domain.ReservationRoom;
import com.trip.hotel_gabriella.common.domain.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class ReservationRoomRepositoryTest {

    @Autowired
    ReservationRoomRepository reservationRoomRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    RoomRepository roomRepository;

    Reservation reservation;

    Room room;

    @BeforeEach
    public void before(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

        reservation = Reservation.builder()
                .name("이민아")
                .phone("01092984423")
                .checkIn(LocalDateTime.parse("202202181400",formatter))
                .checkOut(LocalDateTime.parse("202202231100",formatter))
                .capacity(3)
                .isMember(false)
                .build();


        reservationRepository.save(reservation);


        room = roomRepository.getById(1L);
    }


    @Test
    public void insertReservationRoom() throws Exception {

    //given

        ReservationRoom reservationRoom = ReservationRoom.builder()
                .reservation(reservation)
                .room(room)
                .build();

    //when
        ReservationRoom savedReservationRoom
                = reservationRoomRepository.save(reservationRoom);

        reservationRoomRepository.flush();

        ReservationRoom findReservationRoom
                = reservationRoomRepository
                .findById(savedReservationRoom.getId())
                .orElseThrow(NoSuchElementException::new);

        //then
        assertThat(reservationRoom).isEqualTo(findReservationRoom);
        assertThat(findReservationRoom.getRoom().getNo()).isEqualTo(301);

    }


}