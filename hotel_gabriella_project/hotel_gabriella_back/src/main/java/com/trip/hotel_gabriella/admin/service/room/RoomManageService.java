package com.trip.hotel_gabriella.admin.service.room;

import com.trip.hotel_gabriella.admin.model.room.RoomInfo;
import com.trip.hotel_gabriella.admin.model.room.RoomRegisterRequest;
import com.trip.hotel_gabriella.admin.model.room.RoomRegisterResponse;

import java.util.List;

public interface RoomManageService {

    RoomRegisterResponse registerRoom(RoomRegisterRequest roomRegisterRequest);

    RoomInfo readRoom(Long id);

    List<RoomInfo> readAllRooms();
}
