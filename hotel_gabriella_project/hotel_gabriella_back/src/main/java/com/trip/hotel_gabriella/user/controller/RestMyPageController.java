package com.trip.hotel_gabriella.user.controller;

import com.trip.hotel_gabriella.common.domain.Member;
import com.trip.hotel_gabriella.common.model.MemberInfo;
import com.trip.hotel_gabriella.common.model.TokenPayload;
import com.trip.hotel_gabriella.user.model.member.MyPageReadResponse;
import com.trip.hotel_gabriella.user.repository.MemberRepository;
import com.trip.hotel_gabriella.user.service.member.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class RestMyPageController {
    private final MyPageService myPageService;
    private final MemberRepository memberRepository;

    @PostMapping("/user/myPage")
    public ResponseEntity<MyPageReadResponse> readMyPage(@RequestBody MemberInfo memberInfo){

        MyPageReadResponse myPageReadResponse
                = myPageService.readMyPage(memberInfo.getId());

        System.out.println("myPageReadResponse = " + myPageReadResponse);


        return new ResponseEntity<>(myPageReadResponse, HttpStatus.OK);
    }
}
