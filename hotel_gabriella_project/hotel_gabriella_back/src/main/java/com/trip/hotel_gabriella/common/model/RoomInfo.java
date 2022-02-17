package com.trip.hotel_gabriella.common.model;

import com.querydsl.core.annotations.QueryProjection;
import com.trip.hotel_gabriella.common.domain.Availability;
import com.trip.hotel_gabriella.common.domain.Room;
import com.trip.hotel_gabriella.common.domain.RoomType;
import com.trip.hotel_gabriella.common.domain.ViewType;
import com.trip.hotel_gabriella.common.interfaces.model.GenericResponseEntityAdapter;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoomInfo extends BaseDTO implements GenericResponseEntityAdapter<Room> {
    private Long id;
    private int no;
    private int floor;
    private RoomType roomType;
    private ViewType viewType;
    private int price;
    private Availability availability;
    private int capacity;

    @QueryProjection
    public RoomInfo(Long id, RoomType roomType, ViewType viewType, int price, int capacity) {
        this.id = id;
        this.roomType = roomType;
        this.viewType = viewType;
        this.price = price;
        this.capacity = capacity;
    }

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
