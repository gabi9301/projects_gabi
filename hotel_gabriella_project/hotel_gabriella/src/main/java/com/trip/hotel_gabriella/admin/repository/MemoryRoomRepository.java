package com.trip.hotel_gabriella.admin.repository;

import com.trip.hotel_gabriella.common.domain.Room;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Repository
public class MemoryRoomRepository implements RoomRepository{

    private static Map<Long, Room> storage = new HashMap<>();

    private Long id = 1L;

    @Override
    public void save(Room room) {
        room.setId(id++);
        storage.put(room.getId(), room);
    }

    @Override
    public Room findById(Long id) {
        return storage.get(id);
    }

    @Override
    public List<Room> findAll() {
        List<Room> allRooms = storage.values().stream().collect(Collectors.toList());
        return allRooms;
    }
}
