package com.trip.hotel_gabriella.admin.service;



import com.trip.hotel_gabriella.admin.model.RoomRegisterRequest;
import com.trip.hotel_gabriella.common.domain.Room;

import java.util.List;

public interface RoomManageService {

    public Room saveRoom(RoomRegisterRequest roomRegisterRequest);

    public Room readRoom(Long id);

    public List<Room> readAllRooms();
}
