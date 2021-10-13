package com.trip.hotel_gabriella.admin.service;



import com.trip.hotel_gabriella.common.domain.Room;

import java.util.List;

public interface RegisterService {

    public void saveRoom(Room room);

    public Room readRoom(Long id);

    public List<Room> readAllRooms();
}
