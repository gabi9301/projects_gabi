package com.trip.hotel_gabriella.common.exception.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class  CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(
            HttpServletRequest request
            , HttpServletResponse response
            , AuthenticationException authException
    ) throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);

        if(requestURI.contains("/admin/")){
            response.sendRedirect("/accessDeniedException");
        }

        if(requestURI.contains("/member/")){
            response.sendRedirect("/login");
        }
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
}
