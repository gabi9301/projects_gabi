package com.trip.hotel_gabriella.admin.service.room;

import com.trip.hotel_gabriella.admin.model.room.RoomDetails;
import com.trip.hotel_gabriella.admin.model.room.RoomRegisterRequest;
import com.trip.hotel_gabriella.admin.model.room.RoomRegisterResponse;

import java.util.List;

public interface RoomManageService {

    public RoomRegisterResponse registerRoom(RoomRegisterRequest roomRegisterRequest);

    public RoomDetails readRoom(Long id);

    public List<RoomDetails> readAllRooms();
}
