package com.trip.hotel_gabriella.admin.model;

import com.trip.hotel_gabriella.common.domain.Room;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
@Getter
public class RoomRegisterRequest {

    @NotBlank(message = "방 번호는 필수 항목입니다.")
    private int no;
    private int floor;
    private String roomType;
    private String viewType;

    public RoomRegisterRequest() {
    }

    public RoomRegisterRequest(int no, int floor, String roomType, String viewType) {
        this.no = no;
        this.floor = floor;
        this.roomType = roomType;
        this.viewType = viewType;
    }

    public Room toRoomEntity() {
        return Room.builder()
                .no(no)
                .floor(floor)
                .roomType(roomType)
                .viewType(viewType)
                .build();
    }
}
