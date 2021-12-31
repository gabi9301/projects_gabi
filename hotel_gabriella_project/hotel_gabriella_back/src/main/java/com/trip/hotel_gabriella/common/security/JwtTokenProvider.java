package com.trip.hotel_gabriella.common.security;

import com.trip.hotel_gabriella.common.interfaces.service.RedisService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

//@Component
@RequiredArgsConstructor
public class JwtTokenProvider { //JWT 토큰의 생성과 유효성 검사를 책임진다.

    private SecretKey secretKey;

    private String base64Key;

    @Value("${spring.jwt.expire.accessTokenValidMilliSeconds}")
    private long accessTokenValidMilliSeconds;

    @Value("${spring.jwt.expire.refreshTokenValidMilliSeconds}")
    private long refreshTokenValidMilliSeconds;

    private final UserDetailsService userDetailsService;

    private final RedisService redisService;

    public Map<String, Object> createToken(UserAuthInfo userAuthInfo) {   //토큰을 생성한다
        String accessToken = createAccessToken(userAuthInfo);
        String refreshToken = createRefreshToken(userAuthInfo);
        System.out.println("refreshTokenValidMilliSeconds = " + refreshTokenValidMilliSeconds);
        redisService.setData(userAuthInfo.getAccount(),refreshToken,refreshTokenValidMilliSeconds);

        Map<String ,Object> tokenMap = new HashMap<>();

        tokenMap.put("userAccToken",accessToken);
        tokenMap.put("userRefToken", refreshToken);

        return tokenMap;
    }

    public String createAccessToken(UserAuthInfo userAuthInfo) {
        secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        Claims claims = Jwts.claims().setSubject(userAuthInfo.getAccount());
        claims.put("roles", userAuthInfo.getAuthorities());
        claims.put("extraInfo", userAuthInfo.getExtraInfo());
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessTokenValidMilliSeconds))
                .signWith(secretKey)
                .compact();
    }


    public String createRefreshToken(UserAuthInfo userAuthInfo) {
        Date now = new Date();
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenValidMilliSeconds))
                .signWith(secretKey)
                .compact();

    }




    public Map<String,String> resolveToken(HttpServletRequest request) {
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("accessToken", resolveAccessToken(request));
        tokenMap.put("refreshToken", resolveRefreshToken(request));
        return tokenMap;
    }

    public String resolveAccessToken(HttpServletRequest request) {
       return request.getHeader("authorization") != null ? request.getHeader("authorization") : null;
    }
    public String resolveRefreshToken(HttpServletRequest request) {
       return request.getHeader("authorization") != null ? request.getHeader("refreshToken") : null;
    }





    public String getUserIdentifyKey(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String jwtToken) {

        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(jwtToken);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserIdentifyKey(token));
        return new CustomAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

    public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
        response.setHeader("authorization", accessToken);
    }

    public void setHeaderRefreshToken(HttpServletResponse response, String refreshToken) {
        response.setHeader("refreshToken", refreshToken);
    }


}
