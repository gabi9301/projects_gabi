package com.trip.hotel_gabriella.user.controller;

import com.trip.hotel_gabriella.common.security.*;
import com.trip.hotel_gabriella.user.service.member.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemberAuthController {//JWT 토큰 방식으로 로그인 시 해당 컨트롤러를 통한다

    private final MemberDetailsService memberDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private CustomAuthenticationProvider authProvider;

//    CustomAuthenticationProvider authProvider   //Config에서 싱글톤으로 설정해주지 않은 이유는 admin 로그인 시
//                                                //memberDetailService가 adminDetailsService로 바뀌어야 하기 떄문
//            = new CustomAuthenticationProvider(memberDetailsService,passwordEncoder,jwtTokenProvider);

    @PostConstruct
    public void init() {
        authProvider
                = new CustomAuthenticationProvider(memberDetailsService, passwordEncoder, jwtTokenProvider);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginMember(
            @RequestBody @Valid LoginCommand loginCommand) {

        Map<String, String> authToken = authProvider.authenticate(loginCommand);
        return new ResponseEntity<>(authToken, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/reissue",  method = { RequestMethod.GET, RequestMethod.POST },
            produces = "application/json")
    public ResponseEntity<Map<String,String>> reissueToken(
            @RequestBody @Valid TokenPayload tokenPayload ){
        TokenReissueRequest tokenReissueRequest = new TokenReissueRequest(tokenPayload,null);

        System.out.println("tokenReissueRequest = " + tokenReissueRequest);

        tokenReissueRequest.changeServiceIdentifier("member");
        Map<String,String> newAuthToken = authProvider.reissue(tokenReissueRequest);
        return new ResponseEntity<>(newAuthToken,HttpStatus.CREATED);
    }



}
