package com.trip.hotel_gabriella.user.controller;

import com.trip.hotel_gabriella.common.security.CustomAuthenticationProvider;
import com.trip.hotel_gabriella.common.security.JwtTokenProvider;
import com.trip.hotel_gabriella.common.security.LoginCommand;
import com.trip.hotel_gabriella.user.service.member.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberAuthController {//JWT 토큰 방식으로 로그인 시 해당 컨트롤러를 통한다

    private final MemberDetailsService memberDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider_member;
    private CustomAuthenticationProvider authProvider;

//    CustomAuthenticationProvider authProvider   //Config에서 싱글톤으로 설정해주지 않은 이유는 admin 로그인 시
//                                                //memberDetailService가 adminDetailsService로 바뀌어야 하기 떄문
//            = new CustomAuthenticationProvider(memberDetailsService,passwordEncoder,jwtTokenProvider);

    @PostConstruct
    public void init() {
        authProvider
                = new CustomAuthenticationProvider(memberDetailsService,passwordEncoder,jwtTokenProvider_member);
    }
@PostMapping("/login")
public ResponseEntity<Map<String,Object>> loginMember(
        @RequestBody @Valid LoginCommand loginCommand){

    Map<String,Object> authToken = authProvider.authenticate(loginCommand);

    return new ResponseEntity<>(authToken,HttpStatus.CREATED);
}


}
