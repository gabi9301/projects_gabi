package com.trip.hotel_gabriella.admin.service;


import com.trip.hotel_gabriella.admin.repository.RoomRepository;
import com.trip.hotel_gabriella.common.domain.Availability;
import com.trip.hotel_gabriella.common.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegisterServiceImpl implements RegisterService{

    private final RoomRepository roomRepository;

    @Transactional
    public void saveRoom(Room room){ roomRepository.save(room);}

    @Override
    public Room readRoom(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public List<Room> readAllRooms() {
        return roomRepository.findAll();
    }
}
