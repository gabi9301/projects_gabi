package com.trip.hotel_gabriella.admin.service;



import com.trip.hotel_gabriella.admin.model.RoomDetails;
import com.trip.hotel_gabriella.admin.model.RoomRegisterRequest;
import com.trip.hotel_gabriella.admin.model.RoomRegisterResponse;

import java.util.List;

public interface RoomManageService {

    public RoomRegisterResponse saveRoom(RoomRegisterRequest roomRegisterRequest);

    public RoomDetails readRoom(Long id);

    public List<RoomDetails> readAllRooms();
}
