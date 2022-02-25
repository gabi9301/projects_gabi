package com.trip.hotel_gabriella.user.model.search;

import com.trip.hotel_gabriella.common.domain.Reservation;
import com.trip.hotel_gabriella.common.domain.ReservationRoom;
import com.trip.hotel_gabriella.common.domain.Room;
import com.trip.hotel_gabriella.common.domain.ViewType;
import com.trip.hotel_gabriella.common.interfaces.model.GenericRequestEntityAdapter;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Builder
public class RoomSearchRequest extends BaseDTO implements GenericRequestEntityAdapter<ReservationRoom> {

    @NotBlank(message = "체크인 시간은 필수항목 입니다.")
    private LocalDateTime checkIn;

    @NotBlank(message = "체크아웃 시간은 필수항목 입니다.")
    private LocalDateTime checkOut;

    @NotNull(message = "정원은 필수항목 입니다.")
    @Min(1) @Max(10)
    private int capacity;

    @NotBlank(message = "전망은 필수항목 입니다.")
    private String viewType;

    @Override
    public ReservationRoom toEntity() {

        Reservation reservation = Reservation.builder()
                .capacity(capacity)
                        .build();

        Room room = Room.builder()
                        .viewType(ViewType.valueOf(viewType))
                        .build();

       return ReservationRoom.builder()
                .reservation(reservation)
                .room(room)
                .build();

    }
}
