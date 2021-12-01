package com.trip.hotel_gabriella.common.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class CustomAuthenticationTokenTest {

//    @Autowired
//    public AuthenticationProvider authProvider;

//    @Test
//    @DisplayName("커스텀 토큰에 부가정보가 잘 들어갔는지 확인")
//    public void tokenExtraInfoTest(){
//        Authentication authentication = new CustomAuthenticationToken("user912","passWord32@");
//
//        authProvider.authenticate(authentication);
//
//        CustomAuthenticationToken authInfo = (CustomAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//
//        assertThat(authInfo.getExtraInfo().get("id")).isEqualTo(265);
//
//
//    }

}