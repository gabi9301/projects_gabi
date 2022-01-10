package com.trip.hotel_gabriella.common.security;

import com.trip.hotel_gabriella.common.domain.RedisUser;
import com.trip.hotel_gabriella.common.interfaces.service.RedisService;
import com.trip.hotel_gabriella.user.service.admin.AdminDetailsService;
import com.trip.hotel_gabriella.user.service.member.MemberDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Optional;

@SpringBootTest
class JwtTokenProviderTest {

//    @Autowired
//    MemberDetailsService memberDetailsService;
//
//    @Autowired
//    AdminDetailsService adminDetailsService;
//
    @Autowired
    RedisService redisService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Test
    public void createTokenTest() {


        UserAuthInfo memberAuthInfo = new UserAuthInfo("user912","passWord32@");
        memberAuthInfo.setRoles("MEMBER","ADMIN");
        memberAuthInfo.setExtraInfo("process","testing");


        Map<String, String> result_token = jwtTokenProvider.createToken(memberAuthInfo);
        String refreshToken = (String) redisService.getData(memberAuthInfo.getAccount());





    }










}