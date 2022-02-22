package com.trip.hotel_gabriella.user.service.reservation;

import com.trip.hotel_gabriella.common.model.BookingInfo;
import com.trip.hotel_gabriella.common.model.ReservationInfo;
import com.trip.hotel_gabriella.common.model.RoomInfo;
import com.trip.hotel_gabriella.user.model.reservation.BookingCommand;
import com.trip.hotel_gabriella.user.model.reservation.BookingResponse;
import com.trip.hotel_gabriella.user.model.reservation.ReserveRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class BookingServiceTest {


    @Autowired
    BookingService bookingService;

    BookingCommand bookingCommand;

    ReserveRequest reserveRequest;

    @BeforeEach
    public void setup(){
        reserveRequest =
                ReserveRequest.builder()
                        .checkIn("20220221")
                        .checkOut("20220223")
                        .name("Yujin")
                        .phone("01093920423")
                        .capacity(4)
                        .isMember(false)
                        .build();

        bookingCommand
                = BookingCommand.builder()
                .reserveRequest(reserveRequest)
                .roomId(17L)
                .build();
    }

    @Test
    public void bookTest() throws Exception {
    //given
        BookingResponse bookingResponse
                = bookingService.bookReservation(bookingCommand);

        BookingInfo bookingInfo
                = bookingService.bookSearch(bookingResponse.getReserveId());

        //when

        ReservationInfo reservationInfo =bookingInfo.getReservationInfo();

        RoomInfo roomInfo = bookingInfo.getRoomInfo();

        //then

        assertThat(roomInfo.getNo()).isEqualTo(401);
        assertThat(reservationInfo.getName()).isEqualTo("Yujin");

    }
    
    
    @Test
    public void readTest() throws Exception {
    //given

    //when
    
    //then
    }

}