package com.trip.hotel_gabriella.admin.controller;


import com.trip.hotel_gabriella.admin.model.RoomRegisterRequest;
import com.trip.hotel_gabriella.admin.service.RoomManageService;
import com.trip.hotel_gabriella.common.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //json이나 xml 타입으로 response body를 넘겨준다는 것, 그리고 뷰가 아니라 데이터를 리턴
@RequiredArgsConstructor
public class RestRegisterController {

    private final RoomManageService roomManageService;
//
//    @PostMapping("/register.do")
//    public void registerRoom(@ModelAttribute Room room){
//       registerService.saveRoom(room);
//    }



    @PostMapping("/availableRooms")
    public ResponseEntity<Integer> availableRoomNumber() {
        int availableRoomNumber = roomManageService.readAllRooms().size();
        return new ResponseEntity<>(availableRoomNumber, HttpStatus.OK);
    }

    @PostMapping("/admin/register.do")
    public ResponseEntity<Void> registerRoom(
            @RequestBody @Valid RoomRegisterRequest roomRegisterRequest){
        roomManageService.saveRoom(roomRegisterRequest);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
