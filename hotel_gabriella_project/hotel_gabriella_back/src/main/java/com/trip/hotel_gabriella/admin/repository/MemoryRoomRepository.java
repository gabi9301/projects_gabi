package com.trip.hotel_gabriella.admin.repository;

import com.trip.hotel_gabriella.common.domain.Room;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MemoryRoomRepository{

    private static Map<Long, Room> storage = new HashMap<>();

    private Long id = 1L;

    public void save(Room room) {
        room = new Room(id++
                ,room.getNo()
                ,room.getFloor()
                ,room.getRoomType()
                ,room.getViewType()
                ,room.getPrice()
                ,room.getCapacity()
                ,room.getAvailability());
        storage.put(room.getId(), room);
    }

    public Room findById(Long id) {
        return storage.get(id);
    }

    public List<Room> findAll() {
        List<Room> allRooms = storage.values().stream().collect(Collectors.toList());
        return allRooms;
    }
}
