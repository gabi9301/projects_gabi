package com.trip.hotel_gabriella.admin.controller;


import com.trip.hotel_gabriella.admin.service.RegisterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //json이나 xml 타입으로 response body를 넘겨준다는 것, 그리고 뷰가 아니라 데이터를 리턴
public class RestRegisterController {

    private final RegisterService registerService;

    public RestRegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }
//
//    @PostMapping("/register.do")
//    public void registerRoom(@ModelAttribute Room room){
//       registerService.saveRoom(room);
//    }

    @PostMapping("/availableRooms")
    public String availableRoomNumber() {
        String availableRoomNumber = String.valueOf(
                registerService.readAllRooms().size());
        return availableRoomNumber;
    }
}
