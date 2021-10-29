package com.trip.hotel_gabriella.admin.service.room;

import lombok.RequiredArgsConstructor;

import com.trip.hotel_gabriella.admin.model.room.RoomDetails;
import com.trip.hotel_gabriella.admin.model.room.RoomRegisterRequest;
import com.trip.hotel_gabriella.admin.model.room.RoomRegisterResponse;
import com.trip.hotel_gabriella.admin.repository.RoomRepository;
import com.trip.hotel_gabriella.common.domain.Room;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasicRoomManageService implements RoomManageService {

    private final RoomRepository roomRepository;

    @Transactional
    public RoomRegisterResponse registerRoom(RoomRegisterRequest roomRegisterRequest){
        RoomRegisterResponse result = null;

        Room room = roomRegisterRequest.toEntity();
        roomRepository.save(room);

        result = new RoomRegisterResponse().fromEntity(room);
        return result;
    }

    @Override
    public RoomDetails readRoom(Long id) {
        RoomDetails result = null;

        Room room = roomRepository.findById(id);
        result = new RoomDetails().fromEntity(room);
        return result;
    }

    @Override
    public List<RoomDetails> readAllRooms() {
        List<RoomDetails> result = new ArrayList<>();
        List<Room> rooms = roomRepository.findAll();
        for (Room room : rooms) {
            result.add(new RoomDetails().fromEntity(room));
        }
        return result;
    }
}
