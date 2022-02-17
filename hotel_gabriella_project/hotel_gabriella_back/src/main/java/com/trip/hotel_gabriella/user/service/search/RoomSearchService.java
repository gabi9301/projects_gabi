package com.trip.hotel_gabriella.user.service.search;

import com.trip.hotel_gabriella.common.model.RoomInfo;
import com.trip.hotel_gabriella.user.model.search.RoomSearchRequest;
import com.trip.hotel_gabriella.user.model.search.RoomSearchResponse;

import java.util.List;

public interface RoomSearchService {

    RoomSearchResponse findSuitableRooms(RoomSearchRequest roomSearchRequest);





}
