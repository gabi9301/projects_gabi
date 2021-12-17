package com.trip.hotel_gabriella.common.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;

//@Component
@RequiredArgsConstructor
public class JwtTokenProvider { //JWT 토큰의 생성과 유효성 검사를 책임진다.

    private SecretKey secretKey;

    private String base64Key;

    @Value("${jwt.tokenValidMilliSeconds}")
    private long tokenValidMilliSeconds;

    private final UserDetailsService userDetailsService;

    public String createToken(UserAuthInfo userAuthInfo) {   //토큰을 생성한다

        secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

//        base64Key = Encoders.BASE64.encode(secretKey.getEncoded());
//        System.out.println("base64Key = " + base64Key);

        Claims claims = Jwts.claims().setSubject(userAuthInfo.getAccount());
        claims.put("roles", userAuthInfo.getAuthorities());
        claims.put("extraInfo", userAuthInfo.getExtraInfo());
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMilliSeconds))
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
