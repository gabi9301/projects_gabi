package com.trip.hotel_gabriella.admin.model.room;

import com.trip.hotel_gabriella.client.model.BaseDTO;
import com.trip.hotel_gabriella.client.model.member.GenericRequestEntityAdapter;
import com.trip.hotel_gabriella.common.domain.Room;
import lombok.Getter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
public class RoomRegisterRequest extends BaseDTO implements GenericRequestEntityAdapter<Room> {

    @NotNull(message = "방 번호는 필수 항목입니다.")
    @PositiveOrZero(message = "방 번호는 0보다 커야합니다.")
    private int no;
    private int floor;
    private String roomType;
    private String viewType;

    @Override
    public Room toEntity() {
        return Room.builder()
                .no(no)
                .floor(floor)
                .roomType(roomType)
                .viewType(viewType)
                .build();

    }

}
