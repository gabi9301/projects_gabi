package com.trip.hotel_gabriella.admin.repository;

import com.trip.hotel_gabriella.common.domain.Room;

import java.util.List;

public interface RoomRepository {

    public void save(Room room);

    public Room findById(Long id);

    public List<Room> findAll();
}
