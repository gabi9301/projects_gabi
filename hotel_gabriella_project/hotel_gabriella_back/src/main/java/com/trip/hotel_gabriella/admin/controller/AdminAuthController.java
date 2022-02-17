package com.trip.hotel_gabriella.admin.controller;

import com.trip.hotel_gabriella.admin.service.admin.AdminDetailsService;
import com.trip.hotel_gabriella.common.security.CustomAuthenticationProvider;
import com.trip.hotel_gabriella.common.security.JwtTokenProvider;
import com.trip.hotel_gabriella.common.security.LoginCommand;
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
public class AdminAuthController {

    private final AdminDetailsService adminDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    private CustomAuthenticationProvider authProvider;

    @PostConstruct
    public void init() {
        authProvider
                = new CustomAuthenticationProvider(adminDetailsService,passwordEncoder,jwtTokenProvider);
    }


    @PostMapping("/adminLogin")
    public ResponseEntity<Map<String,String>> loginAdmin(
            @RequestBody @Valid LoginCommand loginCommand){

        Map<String,String> authToken = authProvider.authenticate(loginCommand);

        return new ResponseEntity<>(authToken, HttpStatus.CREATED);
    }
}
