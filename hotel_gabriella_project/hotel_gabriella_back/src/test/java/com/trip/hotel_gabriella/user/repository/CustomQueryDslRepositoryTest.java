package com.trip.hotel_gabriella.user.repository;

import com.trip.hotel_gabriella.admin.repository.RoomRepository;
import com.trip.hotel_gabriella.common.domain.*;
import com.trip.hotel_gabriella.common.model.ReservationInfo;
import com.trip.hotel_gabriella.common.model.RoomInfo;
import com.trip.hotel_gabriella.user.model.search.RoomSearchRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;


import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
public class CustomQueryDslRepositoryTest {

    @Autowired
    CustomQueryDslRepository customQueryDslRepository;

    @Autowired
    ReservationRoomRepository reservationRoomRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    MemberRepository memberRepository;

    Reservation reservation;

    RoomSearchRequest roomSearchRequest;

    Room room;

    @BeforeEach
    public void before() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");

        Member user912 = memberRepository.findByAccount("user912")
                .orElseThrow(NoSuchElementException::new);

        reservation = Reservation.builder()
                .name("아무개")
                .phone("01092984423")
                .checkIn(LocalDateTime.parse("202202181400", formatter))
                .checkOut(LocalDateTime.parse("202202231100", formatter))
                .capacity(3)
                .isMember(true)
                .member(user912)
                .build();

        Reservation save = reservationRepository.save(reservation);

        room = roomRepository.findById(6L).orElseThrow(NoSuchElementException::new);

        ReservationRoom reservationRoom = ReservationRoom.builder()
                .reservation(save)
                .room(room)
                .build();

        reservationRoomRepository.save(reservationRoom);


        ReservationRoom reservationRoom1 = reservationRoomRepository.findByReservation_id(save.getId()).orElseThrow(NoSuchElementException::new);
        System.out.println("reservationRoom = " + reservationRoom1.getRoom().getId());


        roomSearchRequest = RoomSearchRequest.builder()
                .checkIn(LocalDateTime.parse("202202181400", formatter))
                .checkOut(LocalDateTime.parse("202202231100", formatter))
                .capacity(3)
                .viewType("OCEAN")
                .build();
    }


    @Test
    public void reservationInfoFetch() throws Exception {
        //given
        List<ReservationInfo> reservationsByMemberId;

        //when
        reservationsByMemberId
                = customQueryDslRepository.findReservationsByMemberId(33L);
        //then
        System.out.println("reservationsByMemberId = " + reservationsByMemberId.get(0).getName());
        assertThat(reservationsByMemberId.size()).isEqualTo(1);

    }

    @Test
    public void searchRoomTest() throws Exception {
        //given

        List<RoomInfo> suitableRooms;

        //when
        suitableRooms
                = customQueryDslRepository.findSuitableRooms(roomSearchRequest);
        //then

        for (RoomInfo suitableRoom : suitableRooms) {
            System.out.println("suitableRoom = " + suitableRoom.getId() + " --" + suitableRoom.getRoomType());
            Assertions.assertThat(suitableRoom.getId()).isNotEqualTo(6L);
        }
    }


}