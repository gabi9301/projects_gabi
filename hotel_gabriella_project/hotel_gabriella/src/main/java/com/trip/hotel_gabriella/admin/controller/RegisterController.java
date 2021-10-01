package com.trip.hotel_gabriella.admin.controller;

import com.trip.hotel_gabriella.admin.domain.Room;
import com.trip.hotel_gabriella.admin.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }


    @GetMapping("/admin/register")
    public String registerPage(){
        return "admin/register";
    }


    @PostMapping("/admin/register.do")
    public String registerRoom(@ModelAttribute Room room, Model model){
        registerService.saveRoom(room);
        List<Room> allRooms = registerService.readAllRooms();

        model.addAttribute("allRooms",allRooms);
        return "admin/registeredRooms";

    }


}
