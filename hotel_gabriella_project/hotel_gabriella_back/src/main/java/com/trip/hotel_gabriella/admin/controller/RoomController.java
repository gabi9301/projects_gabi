package com.trip.hotel_gabriella.admin.controller;

import lombok.RequiredArgsConstructor;

import com.trip.hotel_gabriella.admin.model.room.RoomInfo;
import com.trip.hotel_gabriella.admin.service.room.RoomManageService;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Secured("ROLE_ADMIN")
@Controller
@RequiredArgsConstructor
public class RoomController {

    private final RoomManageService roomManageService;

    @GetMapping("/admin/register")
    public String registerPage(){
        return "admin/register";
    }



    @GetMapping("/admin/registeredRooms")
    public String registeredRoomsList(Model model) {
        List<RoomInfo> allRooms = roomManageService.readAllRooms();
        model.addAttribute("allRooms",allRooms);
        return "admin/registeredRooms";
    }


}
