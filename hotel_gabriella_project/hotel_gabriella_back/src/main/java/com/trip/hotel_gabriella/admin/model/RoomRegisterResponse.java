package com.trip.hotel_gabriella.admin.model;

import com.trip.hotel_gabriella.client.model.BaseDto;
import com.trip.hotel_gabriella.client.model.GenericResponse;
import com.trip.hotel_gabriella.common.domain.BaseEntity;
import com.trip.hotel_gabriella.common.domain.Room;
import lombok.Getter;

@Getter
public class RoomRegisterResponse extends BaseDto implements GenericResponse {

    private Long id;

    @Override
    public BaseDto toDto(BaseEntity entity) {
        Room room = (Room) entity;
        this.id = room.getId();
        return this;
    }
}
