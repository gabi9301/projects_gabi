package com.trip.hotel_gabriella.common.security;

import com.trip.hotel_gabriella.common.domain.RedisUser;
import com.trip.hotel_gabriella.common.interfaces.service.RedisService;
import com.trip.hotel_gabriella.user.service.member.MemberDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Optional;

@SpringBootTest
class JwtTokenProviderTest {

    @Autowired
    MemberDetailsService memberDetailsService;

    @Autowired
    RedisService redisService;

    @Test
    public void createTokenTest() {
        JwtTokenProvider tokenProvider = new JwtTokenProvider(memberDetailsService, redisService);

        UserAuthInfo memberAuthInfo = new UserAuthInfo("user912","passWord32@");
        memberAuthInfo.setRoles("MEMBER","ADMIN");
        memberAuthInfo.setExtraInfo("process","testing");


        Map<String, Object> result_token = tokenProvider.createToken(memberAuthInfo);
        String refreshToken = (String) redisService.getData(memberAuthInfo.getAccount());


        System.out.println("result_token = " + result_token);
        System.out.println("refreshTokenInRedis = " + refreshToken);




    }










}