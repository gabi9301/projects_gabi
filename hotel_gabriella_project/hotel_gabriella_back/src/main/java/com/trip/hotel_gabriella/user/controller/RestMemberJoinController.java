package com.trip.hotel_gabriella.user.controller;

import com.trip.hotel_gabriella.common.model.MemberInfo;
import com.trip.hotel_gabriella.user.service.member.MemberJoinService;
import lombok.RequiredArgsConstructor;

import com.trip.hotel_gabriella.user.model.member.MemberJoinCommand;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RestMemberJoinController {

    private final MemberJoinService memberJoinService;

    @PostMapping("/joinMember")
    public ResponseEntity<Void> joinMember(
            @RequestBody @Valid MemberJoinCommand memberJoinCommand) {

        log.debug("memberJoinCommand = {}", memberJoinCommand.getMemberRegisterRequest());
        memberJoinService.signInMember(memberJoinCommand);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/checkUniqueAccount")
    public ResponseEntity<Boolean> checkUniqueAccount(
            @RequestBody MemberInfo memberInfo) {
        boolean result = memberJoinService.checkUniqueAccount(memberInfo.getAccount());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
