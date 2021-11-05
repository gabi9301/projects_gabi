package com.trip.hotel_gabriella.user.controller;

import com.trip.hotel_gabriella.user.service.member.MemberAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestMemberAuthController {

    private final MemberAuthService memberAuthService;



}
