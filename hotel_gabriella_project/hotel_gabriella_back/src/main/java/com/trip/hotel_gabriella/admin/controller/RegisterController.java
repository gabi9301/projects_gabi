package com.trip.hotel_gabriella.admin.controller;

import com.trip.hotel_gabriella.admin.model.room.RoomDetails;
import com.trip.hotel_gabriella.admin.service.room.RoomManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final RoomManageService roomManageService;

    @GetMapping("/admin/register")
    public String registerPage(){
        return "admin/register";
    }



    @GetMapping("/admin/registeredRooms")
    public String registeredRoomsList(Model model) {
        List<RoomDetails> allRooms = roomManageService.readAllRooms();
        model.addAttribute("allRooms",allRooms);
        return "admin/registeredRooms";
    }


}
