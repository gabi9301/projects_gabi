package com.trip.hotel_gabriella.admin.controller;

import com.trip.hotel_gabriella.admin.model.room.RoomRegisterRequest;
import com.trip.hotel_gabriella.admin.service.room.RoomManageService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController //json이나 xml 타입으로 response body를 넘겨준다는 것, 그리고 뷰가 아니라 데이터를 리턴
@RequiredArgsConstructor
public class RestRoomController {

    private final RoomManageService roomManageService;

    @PostMapping("/availableRooms")
    public ResponseEntity<Integer> availableRoomNumber() {
        int availableRoomNumber = roomManageService.readAllRooms().size();
        return new ResponseEntity<>(availableRoomNumber, HttpStatus.OK);
    }

    @PostMapping("/admin/register.do")
    public ResponseEntity<Long> registerRoom(
            @RequestBody @Valid RoomRegisterRequest roomRegisterRequest){
        Long registeredRoomId = roomManageService.registerRoom(roomRegisterRequest).getId();
        return new ResponseEntity<>(registeredRoomId,HttpStatus.CREATED);

    }
}
