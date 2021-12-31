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
import java.util.Map;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider_member;
    private final JwtTokenProvider jwtTokenProvider_admin;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String url = ((HttpServletRequest) request).getRequestURL().toString();

        JwtTokenProvider jwtTokenProvider;

        if (url.contains("/member/")) {
            jwtTokenProvider = jwtTokenProvider_member;
        } else if (url.contains("/admin/")) {
            jwtTokenProvider = jwtTokenProvider_admin;
        }else{
            jwtTokenProvider = null;
        }



        if (jwtTokenProvider != null) {
            Map<String,String> tokenMap = jwtTokenProvider.resolveToken((HttpServletRequest) request);
            String accessToken = tokenMap.get("authorization");
            String refreshToken = tokenMap.get("refreshToken");

            if(accessToken != null){
                if(jwtTokenProvider.validateToken(accessToken)){
                    this.setAuthentication(accessToken, jwtTokenProvider);
                }else if(!jwtTokenProvider.validateToken(accessToken) && refreshToken != null){
                    //엑세스 토큰은 만료되었지만 refresh 토큰이 있는 경우
                    if(jwtTokenProvider.validateToken(refreshToken)){ // refresh 토큰이 유효한 경우

                    }
                }

            }

        }
        chain.doFilter(request, response);
    }

    public void setAuthentication(String token, JwtTokenProvider jwtTokenProvider) {
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
