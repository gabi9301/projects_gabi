package com.trip.hotel_gabriella.admin.model;

import com.trip.hotel_gabriella.client.model.BaseDto;
import com.trip.hotel_gabriella.client.model.GenericRequest;
import com.trip.hotel_gabriella.common.domain.BaseEntity;
import com.trip.hotel_gabriella.common.domain.Room;
import lombok.Getter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
public class RoomRegisterRequest extends BaseDto implements GenericRequest {

    @NotNull(message = "방 번호는 필수 항목입니다.")
    @PositiveOrZero(message = "방 번호는 0보다 커야합니다.")
    private int no;
    private int floor;
    private String roomType;
    private String viewType;

    @Override
    public BaseEntity toEntity(BaseDto dto) {
        return Room.builder()
                .no(no)
                .floor(floor)
                .roomType(roomType)
                .viewType(viewType)
                .build();

    }

//    public Room toEntity() {
//        return Room.builder()
//                .no(no)
//                .floor(floor)
//                .roomType(roomType)
//                .viewType(viewType)
//                .build();
//    }
}
