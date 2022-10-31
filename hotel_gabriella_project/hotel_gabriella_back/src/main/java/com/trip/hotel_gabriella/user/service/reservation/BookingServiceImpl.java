package com.trip.hotel_gabriella.user.service.reservation;

import com.trip.hotel_gabriella.admin.service.room.RoomManageService;
import com.trip.hotel_gabriella.common.domain.*;
import com.trip.hotel_gabriella.common.exception.customException.BookingConditionsNotMatchException;
import com.trip.hotel_gabriella.common.model.BookingInfo;
import com.trip.hotel_gabriella.common.model.ReservationInfo;
import com.trip.hotel_gabriella.common.model.RoomInfo;
import com.trip.hotel_gabriella.common.model.RoomReservationInfo;
import com.trip.hotel_gabriella.user.model.reservation.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Getter
@Transactional(readOnly = true)
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final ReserveService reserveService;

    private final RoomReserveService roomReserveService;

    private final RoomManageService roomManageService;


    @Transactional
    public BookingResponse bookReservation(BookingCommand bookingCommand) {

        RoomInfo roomInfo = roomManageService.readRoom(bookingCommand.getRoomId());

        int capacity = bookingCommand.getReserveRequest().getCapacity();

        ViewType viewType = bookingCommand.getViewType();

        if (capacity > roomInfo.getCapacity()
                || !(viewType.equals(roomInfo.getViewType()))) {
            //사용자 요청 속 정원과 전망 정보가 방 정보와 맞지 않음.
            //요청을 변조했을 수 있음.
            throw new BookingConditionsNotMatchException();
        }

        ReserveResponse reserveResponse
                = reserveService.reserve(bookingCommand.getReserveRequest());

        RoomReserveRequest roomReserveRequest
                = RoomReserveRequest.builder()
                .room(Room.builder().id(bookingCommand.getRoomId()).build())
                .reservation(Reservation.builder().id(reserveResponse.getId()).build())
                .build();


        RoomReserveResponse roomReserveResponse
                = roomReserveService.reserveRoom(roomReserveRequest);

        Long reserveId = reserveResponse.getId();
        Long roomReserveId = roomReserveResponse.getId();

        return BookingResponse.builder()
                .reserveId(reserveId)
                .roomReserveId(roomReserveId)
                .build();

    }

    @Override
    public BookingInfo bookSearch(Long reservationId) {

        ReservationInfo reservationInfo
                = reserveService.readReservation(reservationId);

        RoomReservationInfo roomReservationInfo
                = roomReserveService.readReservedRoom(reservationId);

        RoomInfo roomInfo
                = roomManageService.readRoom(roomReservationInfo.getRoom_id());

        return BookingInfo.builder()
                .reservationInfo(reservationInfo)
                .roomInfo(roomInfo)
                .build();
    }

    @Transactional
    public void bookCancel(Long reservation_Id) {

        reserveService.cancelReservation(reservation_Id);

    }

    @Override
    public List<BookingInfo> BookSearchHistory(ReservationReadRequest reservationReadRequest) {
        List<BookingInfo> bookingInfos = new ArrayList<>();
        List<RoomReservationInfo> roomReservationInfos = new ArrayList<>();

        List<ReservationInfo> reservationInfos
                = reserveService.readReservationByType(reservationReadRequest,ReservationType.BookReservation);
        //이름 전화번호 타입(방 예약)으로 조회한 리스트

        for(ReservationInfo reservationInfo : reservationInfos){
           roomReservationInfos.add(
                   roomReserveService.readReservedRoom(reservationInfo.getId()));
        }
        //위의 리스트에서 얻은 주문 번호로 방아이디를 얻음

        for(RoomReservationInfo roomReservationInfo : roomReservationInfos){
            ReservationInfo reservationInfo
                    = reserveService.readReservation(roomReservationInfo.getReservation_id());
            RoomInfo roomInfo
                    = roomManageService.readRoom(roomReservationInfo.getRoom_id());

            bookingInfos.add(new BookingInfo(reservationInfo,roomInfo));
        }

        return bookingInfos;
    }

}
