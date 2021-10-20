package com.trip.hotel_gabriella.admin.model;

import com.trip.hotel_gabriella.client.model.BaseDto;
import com.trip.hotel_gabriella.client.model.GenericResponse;
import com.trip.hotel_gabriella.common.domain.Availability;
import com.trip.hotel_gabriella.common.domain.BaseEntity;
import com.trip.hotel_gabriella.common.domain.Room;
import lombok.Getter;

@Getter
public class RoomDetails extends BaseDto implements GenericResponse {
    private Long id;
    private int no;
    private int floor;
    private String roomType;
    private String viewType;
    private int price;
    private Availability availability;
    private int capacity;

    @Override
    public BaseDto toDto(BaseEntity entity) {
        Room room = (Room) entity;

        this.id = room.getId();
        this.no = room.getNo();
        this.floor = room.getFloor();
        this.roomType = room.getRoomType();
        this.viewType = room.getViewType();
        this.price = room.getPrice();
        this.availability = room.getAvailability();
        this.capacity = room.getCapacity();

        return this;
    }
}
