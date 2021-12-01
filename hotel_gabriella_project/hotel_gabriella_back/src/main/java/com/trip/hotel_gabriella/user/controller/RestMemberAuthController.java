package com.trip.hotel_gabriella.user.controller;

import com.trip.hotel_gabriella.user.model.member.MemberLoginCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RestMemberAuthController {

    //private final MemberDetailsService memberAuthService;

//    @PostMapping("/login.do")
    @PostMapping("/logi.do")
    public ResponseEntity<Void> loginMember(
            @RequestBody @Valid MemberLoginCommand memberLoginCommand){

        //memberAuthService.memberAuthenticate(memberLoginCommand);

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
