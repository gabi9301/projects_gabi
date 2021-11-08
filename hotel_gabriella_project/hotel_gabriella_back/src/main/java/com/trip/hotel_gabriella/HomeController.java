package com.trip.hotel_gabriella;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
     return "user/public/index";
    }

    @GetMapping("/admin/main")
    public String adminIndex(){
        return "admin/index";
    }
}
