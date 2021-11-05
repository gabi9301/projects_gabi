package com.trip.hotel_gabriella.common.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController {

    @GetMapping("/accessDeniedException")
    public String accessDenied() {

        return "exception/accessDenied";
    }
}
