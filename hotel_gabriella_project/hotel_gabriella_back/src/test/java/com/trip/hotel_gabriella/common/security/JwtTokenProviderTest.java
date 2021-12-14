package com.trip.hotel_gabriella.common.security;

import com.trip.hotel_gabriella.user.model.member.MemberAuthInfo;
import com.trip.hotel_gabriella.user.service.member.MemberDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtTokenProviderTest {

    @Autowired
    MemberDetailsService memberDetailsService;

    @Test
    public void createTokenTest() {
        JwtTokenProvider tokenProvider = new JwtTokenProvider(memberDetailsService);

        MemberAuthInfo memberAuthInfo = new MemberAuthInfo("user912","passWord32@");
        memberAuthInfo.setRoles("MEMBER","ADMIN");
        memberAuthInfo.setExtraInfo("process","testing");


        String result_token = tokenProvider.createToken(memberAuthInfo);

        System.out.println("result_token = " + result_token);


    }










}