package com.trip.hotel_gabriella.common.security;

import com.trip.hotel_gabriella.user.service.admin.AdminDetailsService;
import com.trip.hotel_gabriella.user.service.member.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider1;
    private final JwtTokenProvider getJwtTokenProvider2;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String url = ((HttpServletRequest) request).getRequestURL().toString();

        JwtTokenProvider jwtTokenProvider;

        if (url.contains("/member/")) {
            jwtTokenProvider = jwtTokenProvider1;
        } else if (url.contains("/admin/")) {
            jwtTokenProvider = getJwtTokenProvider2;
        }else{
            jwtTokenProvider = null;
        }

        if (jwtTokenProvider != null) {
            String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}
