package com.trip.hotel_gabriella.user.model.reservation;

import com.trip.hotel_gabriella.common.domain.ReservationRoom;
import com.trip.hotel_gabriella.common.interfaces.model.GenericHttpRequestTransferor;
import com.trip.hotel_gabriella.common.interfaces.model.GenericRequestEntityAdapter;
import com.trip.hotel_gabriella.common.validation.annotation.Phone;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.Getter;

import javax.validation.constraints.NotNull;


@Getter
public class RoomReserveRequest extends BaseDTO implements GenericRequestEntityAdapter<ReservationRoom> {



    @NotNull(message = "방 아이디는 필수항목입니다.")
    private Long roomId;


    @Override
    public ReservationRoom toEntity() {
        return null;
    }
}
