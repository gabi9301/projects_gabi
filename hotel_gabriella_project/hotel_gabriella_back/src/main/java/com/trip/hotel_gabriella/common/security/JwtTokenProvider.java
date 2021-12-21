package com.trip.hotel_gabriella.common.security;

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

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

import java.util.*;

//@Component
@RequiredArgsConstructor
public class JwtTokenProvider { //JWT 토큰의 생성과 유효성 검사를 책임진다.

    private SecretKey secretKey;

    private String base64Key;

    @Value("${jwt.accessTokenValidMilliSeconds}")
    private long accessTokenValidMilliSeconds;

    @Value("${jwt.refreshTokenValidMilliSeconds}")
    private long refreshTokenValidMilliSeconds;

    private final UserDetailsService userDetailsService;

    public Map<String, Object> createToken(UserAuthInfo userAuthInfo) {   //토큰을 생성한다
        String accessToken = createAccessToken(userAuthInfo);
        String refreshToken = createRefreshToken(userAuthInfo);

        List<String> userAccountAccToken = new ArrayList<>();
        userAccountAccToken.add(userAuthInfo.getAccount());
        userAccountAccToken.add(accessToken);

        Map<String ,Object> map = new HashMap<>();

        return map;
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




    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    public String getUserPrimaryKey(String token) {
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
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPrimaryKey(token));
        return new CustomAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }


}
