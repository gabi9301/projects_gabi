package com.trip.hotel_gabriella.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/client/memberJoin")
    public String memberJoinPage() {

        return "client/memberJoin";
    }

}
