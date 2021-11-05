package com.trip.hotel_gabriella.admin.model.room;

import lombok.Getter;

import com.trip.hotel_gabriella.user.model.BaseDTO;
import com.trip.hotel_gabriella.common.interfaces.model.GenericResponseEntityAdapter;
import com.trip.hotel_gabriella.common.domain.Room;

@Getter
public class RoomRegisterResponse extends BaseDTO implements GenericResponseEntityAdapter<Room> {

    private Long id;

    @Override
    public RoomRegisterResponse fromEntity(Room room) {
        this.id = room.getId();
        return this;
    }


}
