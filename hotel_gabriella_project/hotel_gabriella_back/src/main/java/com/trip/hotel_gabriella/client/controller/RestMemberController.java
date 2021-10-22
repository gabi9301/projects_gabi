package com.trip.hotel_gabriella.client.controller;

import com.trip.hotel_gabriella.client.model.member.MemberJoinRequest;
import com.trip.hotel_gabriella.client.model.member.MemberRegisterRequest;
import com.trip.hotel_gabriella.client.service.member.BasicMemberJoinService;
import lombok.RequiredArgsConstructor;
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

        System.out.println("memberJoinRequest.getMemberRegisterRequest().getAccount() = " + memberJoinRequest.getMemberRegisterRequest().getAccount());
        System.out.println("memberJoinRequest.getTermsRegisterRequest() = " + memberJoinRequest.getTermsRegisterRequest());
        
        //System.out.println("memberJoinRequest.account= " + memberJoinRequest.getMemberRegisterRequest().getAccount());

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
