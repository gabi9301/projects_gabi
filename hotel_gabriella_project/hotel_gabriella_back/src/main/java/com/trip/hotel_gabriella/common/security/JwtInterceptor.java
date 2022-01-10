package com.trip.hotel_gabriella.common.security;

import com.trip.hotel_gabriella.common.interfaces.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;


//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String url = request.getRequestURL().toString();
//        String detailsServiceIdentifier = "none";
//
//
//        if (url.contains("/member/") || url.contains("/admin/")) {
//
//            if (url.contains("/member/")) {
//                detailsServiceIdentifier = "member";
//            }
//            if (url.contains("/admin/")) {
//                detailsServiceIdentifier = "admin";
//            }
//        }
//
//        TokenReissueRequest tokenReissueRequest = (TokenReissueRequest) request.getAttribute("TokenReissueRequest");
//        tokenReissueRequest.changeServiceIdentifier(detailsServiceIdentifier);
//
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//    }
//
//    public void setAuthentication(String token, String serviceIdentifier) {
//        Authentication authentication = jwtTokenProvider.getAuthentication(token, serviceIdentifier);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }
}
