package com.trip.hotel_gabriella.admin.controller;

import com.trip.hotel_gabriella.admin.model.RoomRegisterRequest;
import com.trip.hotel_gabriella.admin.service.RoomManageService;
import com.trip.hotel_gabriella.common.domain.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final RoomManageService roomManageService;

    @GetMapping("/admin/register")
    public String registerPage(){
        return "admin/register";
    }


    @PostMapping("/admin/register.do")
    public String registerRoom(@Valid RoomRegisterRequest roomRegisterRequest, Model model){
        roomManageService.saveRoom(roomRegisterRequest);
        List<Room> allRooms = roomManageService.readAllRooms();

        model.addAttribute("allRooms",allRooms);
        return "admin/registeredRooms";

    }


}
