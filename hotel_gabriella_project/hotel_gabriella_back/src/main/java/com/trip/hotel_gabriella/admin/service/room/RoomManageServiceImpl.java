package com.trip.hotel_gabriella.admin.service.room;

import com.trip.hotel_gabriella.admin.model.room.RoomRegisterRequest;
import com.trip.hotel_gabriella.admin.model.room.RoomRegisterResponse;
import com.trip.hotel_gabriella.admin.repository.RoomRepository;
import com.trip.hotel_gabriella.common.domain.Room;

import com.trip.hotel_gabriella.common.model.RoomInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomManageServiceImpl implements RoomManageService {

    private  final RoomRepository roomRepository;

    @Transactional
    public RoomRegisterResponse registerRoom(RoomRegisterRequest roomRegisterRequest){

        Room room = roomRegisterRequest.toEntity();
        roomRepository.save(room);

        return new RoomRegisterResponse().fromEntity(room);
    }

    @Override
    public RoomInfo readRoom(Long id) {

        Room room = roomRepository.getById(id);

        return new RoomInfo().fromEntity(room);
    }

    @Override
    public List<RoomInfo> readAllRooms() {
        List<RoomInfo> result = new ArrayList<>();
        List<Room> rooms = roomRepository.findAll();
        for (Room room : rooms) {
            result.add(new RoomInfo().fromEntity(room));
        }
        return result;
    }
}
