package com.trip.hotel_gabriella.user.controller;

import com.trip.hotel_gabriella.user.service.member.MemberJoinService;
import lombok.RequiredArgsConstructor;

import com.trip.hotel_gabriella.user.model.member.MemberJoinCommand;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RestMemberJoinController {

    private final MemberJoinService memberJoinService;
    
    @PostMapping("/join.do")
    public ResponseEntity<Void> joinMember(
            @RequestBody @Valid MemberJoinCommand memberJoinCommand) {

        memberJoinService.signInMember(memberJoinCommand);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
