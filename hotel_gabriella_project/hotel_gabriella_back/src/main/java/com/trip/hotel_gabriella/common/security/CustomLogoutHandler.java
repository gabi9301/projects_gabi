package com.trip.hotel_gabriella.common.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class CustomLogoutHandler implements LogoutHandler {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String accessToken = jwtTokenProvider.resolveAccessToken(request);
        log.debug("accessToken_before_logout={} " + accessToken);

        jwtTokenProvider.invalidateToken(accessToken);

    }
}
