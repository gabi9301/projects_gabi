package com.trip.hotel_gabriella.admin.service;


import com.trip.hotel_gabriella.admin.model.RoomDetails;
import com.trip.hotel_gabriella.admin.model.RoomRegisterRequest;
import com.trip.hotel_gabriella.admin.model.RoomRegisterResponse;
import com.trip.hotel_gabriella.admin.repository.RoomRepository;
import com.trip.hotel_gabriella.common.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasicRoomManageService implements RoomManageService {

    private final RoomRepository roomRepository;

    @Transactional
    public RoomRegisterResponse saveRoom(RoomRegisterRequest roomRegisterRequest){
        RoomRegisterResponse result = null;

        Room room = (Room)roomRegisterRequest.toEntity(roomRegisterRequest);
        roomRepository.save(room);

        result = (RoomRegisterResponse) new RoomRegisterResponse().toDto(room);
        return result;
    }

    @Override
    public RoomDetails readRoom(Long id) {
        RoomDetails result = null;

        Room room = roomRepository.findById(id);
        result = (RoomDetails) new RoomDetails().toDto(room);
        return result;
    }

    @Override
    public List<RoomDetails> readAllRooms() {
        List<RoomDetails> result = new ArrayList<>();
        List<Room> rooms = roomRepository.findAll();
        for (Room room : rooms) {
            result.add((RoomDetails) new RoomDetails().toDto(room));
        }
        return result;
    }
}
