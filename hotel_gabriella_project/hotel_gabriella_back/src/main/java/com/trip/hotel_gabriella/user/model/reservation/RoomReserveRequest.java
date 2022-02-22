package com.trip.hotel_gabriella.user.model.reservation;

import com.trip.hotel_gabriella.common.domain.Reservation;
import com.trip.hotel_gabriella.common.domain.ReservationRoom;
import com.trip.hotel_gabriella.common.domain.Room;
import com.trip.hotel_gabriella.common.interfaces.model.GenericHttpRequestTransferor;
import com.trip.hotel_gabriella.common.interfaces.model.GenericRequestEntityAdapter;
import com.trip.hotel_gabriella.common.validation.annotation.Phone;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Getter
@Builder
public class RoomReserveRequest extends BaseDTO implements GenericRequestEntityAdapter<ReservationRoom> {


//    @NotNull(message = "방 아이디는 필수항목입니다.")
//    private Long roomId;
//
//    @NotNull(message = "예약 아이디는 필수항목입니다.")
//    private Long reservationId;

    @Valid
    private Reservation reservation;

    @Valid
    private Room room;



    @Override
    public ReservationRoom toEntity() {
        return ReservationRoom.builder()
                .room(room)
                .reservation(reservation)
                .build();
    }
}
