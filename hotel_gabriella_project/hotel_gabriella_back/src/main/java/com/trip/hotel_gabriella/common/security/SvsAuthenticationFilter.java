package com.trip.hotel_gabriella.common.security;

import com.trip.hotel_gabriella.user.service.admin.AdminDetailsService;
import com.trip.hotel_gabriella.user.service.member.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class SvsAuthenticationFilter extends GenericFilterBean {

    private final MemberDetailsService memberDetailsService;
    private final AdminDetailsService adminDetailsService;
    private final PasswordEncoder passwordEncoder;
    private CustomAuthenticationProvider authProvider;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String url = ((HttpServletRequest) request).getRequestURL().toString();

        if (url.contains("/login.do")) {
            authProvider = new CustomAuthenticationProvider(memberDetailsService,passwordEncoder,null);
        } else if (url.contains("/adminLogin.do")) {
            authProvider = new CustomAuthenticationProvider(adminDetailsService,passwordEncoder,null);
        }

        if(authProvider != null){

        }


        chain.doFilter(request,response);
    }
}
