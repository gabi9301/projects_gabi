package com.trip.hotel_gabriella.admin.model.room;

import lombok.Getter;

import com.trip.hotel_gabriella.user.model.BaseDTO;
import com.trip.hotel_gabriella.common.interfaces.model.GenericResponseEntityAdapter;
import com.trip.hotel_gabriella.common.domain.Availability;
import com.trip.hotel_gabriella.common.domain.Room;

@Getter
public class RoomInfo extends BaseDTO implements GenericResponseEntityAdapter<Room> {
    private Long id;
    private int no;
    private int floor;
    private String roomType;
    private String viewType;
    private int price;
    private Availability availability;
    private int capacity;

    @Override
    public RoomInfo fromEntity(Room room) {

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
