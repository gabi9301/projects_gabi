package com.trip.hotel_gabriella.common.security;

import com.trip.hotel_gabriella.common.interfaces.service.RedisService;
import com.trip.hotel_gabriella.common.util.RequestWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisService redisService;

    private String detailsServiceIdentifier = "none";


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        String url = ((HttpServletRequest) request).getRequestURL().toString();

        boolean shouldValidate = false;

        if (url.contains("/member/") || url.contains("/admin/")) {
            shouldValidate = true;
            if (url.contains("/member/")) {
                detailsServiceIdentifier = "member";
            }
            if (url.contains("/admin/")) {
                detailsServiceIdentifier = "admin";
            }
        }

        if (shouldValidate) {
            Map<String, String> tokenMap = jwtTokenProvider.resolveToken((HttpServletRequest) request);
            String accessToken = tokenMap.get("authorization");
            String refreshToken = tokenMap.get("refreshToken");

            if (accessToken != null) {
                //엑세스 토큰이 유효할 경우
                if (jwtTokenProvider.validateToken(accessToken)) {
                    this.setAuthentication(accessToken, detailsServiceIdentifier);

                    chain.doFilter(request, response);
                } else {
                    //엑세스 토큰은 만료되었지만 refresh 토큰이 있는 경우

                    if (refreshToken != null && jwtTokenProvider.validateToken(refreshToken)) { // refresh 토큰이 유효한 경우
                        String forwardUri = "";

                        if (detailsServiceIdentifier.equals("member")) {
                            forwardUri = "/reissue";
                        }
                        if (detailsServiceIdentifier.equals("admin")) {
                            forwardUri = "/adminReissue";
                        }




                        TokenPayload tokenPayload = new TokenPayload(accessToken,refreshToken);


                        request = new RequestWrapper((HttpServletRequest) request, tokenPayload);

                        

                        System.out.println("request = " + ((RequestWrapper) request).getBody());





                        RequestDispatcher requestDispatcher = request.getRequestDispatcher(forwardUri);


                        requestDispatcher.forward(request, response);

                        return;
                    }
                }
            }
        }
        chain.doFilter(request, response);

    }

    public void setAuthentication(String token, String serviceIdentifier) {
        Authentication authentication = jwtTokenProvider.getAuthentication(token, serviceIdentifier);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
