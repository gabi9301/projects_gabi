package com.trip.hotel_gabriella.user.service.search;

import com.trip.hotel_gabriella.common.model.RoomInfo;
import com.trip.hotel_gabriella.user.model.search.RoomSearchCommand;
import com.trip.hotel_gabriella.user.model.search.RoomSearchRequest;
import com.trip.hotel_gabriella.user.model.search.RoomSearchResponse;
import com.trip.hotel_gabriella.user.repository.CustomQueryDslRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoomSearchServiceImpl implements RoomSearchService{

    private final CustomQueryDslRepository customQueryRoomRepository;

    @Value("${hotel.time.defaultCheckInHour}")
    private String defaultCheckInHour;

    @Value("${hotel.time.defaultCheckOutHour}")
    private String defaultCheckOutHour;

    @Override
    public RoomSearchResponse findSuitableRooms(RoomSearchCommand roomSearchCommand) {

        RoomSearchRequest roomSearchRequest
                = RoomSearchRequest.builder()
                .checkIn(checkTimeParse(roomSearchCommand.getCheckIn(),defaultCheckInHour))
                .checkOut(checkTimeParse(roomSearchCommand.getCheckOut(),defaultCheckOutHour))
                .capacity(roomSearchCommand.getCapacity())
                .viewType(roomSearchCommand.getViewType())
                .build();

        List<RoomInfo> suitableRooms
                = customQueryRoomRepository.findSuitableRooms(roomSearchRequest);

        return RoomSearchResponse.builder()
                .suitableRooms(suitableRooms)
                .build();
    }

    public LocalDateTime checkTimeParse(String checkDate, String checkHour){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        return LocalDateTime.parse(checkDate + checkHour, formatter);
    }
}
