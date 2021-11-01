package com.trip.hotel_gabriella.admin.repository;

import com.trip.hotel_gabriella.common.domain.Room;

import java.util.List;

public interface RoomRepository {

    void save(Room room);

    Room findById(Long id);

    List<Room> findAll();
}
