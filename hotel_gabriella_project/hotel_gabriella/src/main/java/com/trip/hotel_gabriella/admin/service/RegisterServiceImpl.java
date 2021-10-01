package com.trip.hotel_gabriella.admin.service;

import com.trip.hotel_gabriella.admin.domain.Room;
import com.trip.hotel_gabriella.admin.repository.RoomRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Service
@Transactional(readOnly = true)
public class RegisterServiceImpl implements RegisterService{

    private final RoomRepository roomRepository;

    public RegisterServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Transactional
    public void saveRoom(Room room){
        roomRepository.save(room);
    }

    @Override
    public Room readRoom(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public List<Room> readAllRooms() {
        return roomRepository.findAll();
    }
}
