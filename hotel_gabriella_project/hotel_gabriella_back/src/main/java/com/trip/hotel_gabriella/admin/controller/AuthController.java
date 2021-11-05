package com.trip.hotel_gabriella.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/adminAuth")
    public String adminAuthPage(){
        return "admin/adminAuth";
    }
}
