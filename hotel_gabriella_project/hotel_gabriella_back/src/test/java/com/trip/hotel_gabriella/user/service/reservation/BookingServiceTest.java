package com.trip.hotel_gabriella.user.service.reservation;

import com.trip.hotel_gabriella.common.domain.ViewType;
import com.trip.hotel_gabriella.common.model.BookingInfo;
import com.trip.hotel_gabriella.common.model.ReservationInfo;
import com.trip.hotel_gabriella.common.model.RoomInfo;
import com.trip.hotel_gabriella.user.model.reservation.BookingCommand;
import com.trip.hotel_gabriella.user.model.reservation.BookingResponse;
import com.trip.hotel_gabriella.user.model.reservation.ReservationReadRequest;
import com.trip.hotel_gabriella.user.model.reservation.ReserveRequest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
public class BookingServiceTest {


    @Autowired
    BookingService bookingService;

    BookingCommand bookingCommand;

    ReserveRequest reserveRequest;

    //@BeforeEach
    public void setup() {
        reserveRequest =
                ReserveRequest.builder()
                        .checkIn("20220921")
                        .checkOut("20220923")
                        .name("Yujin")
                        .phone("01093920423")
                        .capacity(4)
                        .isMember(false)
                        .build();

        bookingCommand
                = BookingCommand.builder()
                .reserveRequest(reserveRequest)
                .viewType(ViewType.OCEAN)
                .roomId(23L)
                .build();
    }

    @Test
    public void bookReadTest() throws Exception {
        //given
        BookingResponse bookingResponse
                = bookingService.bookReservation(bookingCommand);

        BookingInfo bookingInfo
                = bookingService.bookSearch(bookingResponse.getReserveId());

        //when

        ReservationInfo reservationInfo = bookingInfo.getReservationInfo();

        RoomInfo roomInfo = bookingInfo.getRoomInfo();

        //then

        assertThat(roomInfo.getNo()).isEqualTo(407);
        assertThat(reservationInfo.getName()).isEqualTo("Yujin");

    }

    @Test
    public void bookCancelTest() throws Exception {
        //given
        BookingResponse bookingResponse
                = bookingService.bookReservation(bookingCommand);

        //when
        bookingService.bookCancel(bookingResponse.getReserveId());

        //then

        BookingInfo bookingInfo
                = bookingService.bookSearch(bookingResponse.getReserveId());

        assertThat(bookingInfo.getReservationInfo().isCanceled()).isTrue();
    }

    @Test
    public void bookReadHistoryTest() throws Exception{
        ReservationReadRequest reservationReadRequest
                = ReservationReadRequest.builder()
                .name("Yujin")
                        .phone("01093920423").build();

        List<BookingInfo> bookingInfos = bookingService.bookSearchHistory(reservationReadRequest);

        for(BookingInfo bookingInfo : bookingInfos){
            log.debug("-----------------------!!!-booking viewType = {}", bookingInfo.getRoomInfo().getViewType());
        }

    }


}