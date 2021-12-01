package com.trip.hotel_gabriella;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "user/public/index";
    }

    @GetMapping("/joinMember")
    public String memberJoinPage() {
        return "user/public/memberJoin";
    }

    @GetMapping("/login")
    public String memberLoginPage() {
        return "user/public/login";
    }

    @GetMapping("/{page}.view")
    public String movePage(@PathVariable("page") String returnPage) {
        return returnPage;
    }

    @GetMapping("/{depth}/{page}.view")
    public String movePage(@PathVariable("depth") String depth, @PathVariable("page") String returnPage) {
        return depth + "/" + returnPage;
    }

    @GetMapping("/{depth1}/{depth2}/{page}.view")
    public String movePage(@PathVariable("depth1") String depth1, @PathVariable("depth2") String depth2, @PathVariable("page") String returnPage) {
        return depth1 + "/" + depth2 + "/" + returnPage;
    }


}
