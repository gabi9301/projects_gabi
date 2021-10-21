package com.trip.hotel_gabriella.admin.model.room;

import com.trip.hotel_gabriella.client.model.BaseDTO;
import com.trip.hotel_gabriella.client.model.member.GenericResponseEntityAdatper;
import com.trip.hotel_gabriella.common.domain.Room;
import lombok.Getter;

@Getter
public class RoomRegisterResponse extends BaseDTO implements GenericResponseEntityAdatper<Room> {

    private Long id;

    @Override
    public RoomRegisterResponse fromEntity(Room room) {
        this.id = room.getId();
        return this;
    }


}
