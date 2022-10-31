package com.trip.hotel_gabriella.user.controller;

import com.trip.hotel_gabriella.user.model.member.MemberJoinCommand;
import com.trip.hotel_gabriella.user.model.search.RoomSearchCommand;
import com.trip.hotel_gabriella.user.model.search.RoomSearchResponse;
import com.trip.hotel_gabriella.user.service.search.RoomSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RestSearchController {

    private final RoomSearchService roomSearchService;

    @PostMapping("/searchRoom")
    public ResponseEntity<RoomSearchResponse> searchRoom(
            @RequestBody @Valid RoomSearchCommand roomSearchCommand) {

        log.debug("roomSearchCommand = {}" , roomSearchCommand.getCheckIn());
        RoomSearchResponse roomSearchResponse
                = roomSearchService.findSuitableRooms(roomSearchCommand);



        return new ResponseEntity<>(roomSearchResponse, HttpStatus.OK);
    }

}
