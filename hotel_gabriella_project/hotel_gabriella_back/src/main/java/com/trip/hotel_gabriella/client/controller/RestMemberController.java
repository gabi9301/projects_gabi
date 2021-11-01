package com.trip.hotel_gabriella.client.controller;

import lombok.RequiredArgsConstructor;

import com.trip.hotel_gabriella.client.model.member.MemberJoinRequest;
import com.trip.hotel_gabriella.client.service.member.BasicMemberJoinService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class RestMemberController {

    private final BasicMemberJoinService memberJoinService;
    
    @PostMapping("/client/joinMember.do")
    public ResponseEntity<Void> joinMember(
            @RequestBody @Valid MemberJoinRequest memberJoinRequest) {

        memberJoinService.signInMember(memberJoinRequest);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
