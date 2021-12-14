package com.trip.hotel_gabriella.common.security;

import com.trip.hotel_gabriella.user.model.member.MemberAuthInfo;
import com.trip.hotel_gabriella.user.service.member.MemberDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider { //JWT 토큰의 생성과 유효성 검사를 책임진다.

    @Value("${jwt.tokenValidMilliSeconds}")
    private static long tokenValidMilliSeconds; //1 hour

    private final MemberDetailsService memberDetailsService;

    public String createToken(MemberAuthInfo memberAuthInfo){

        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        System.out.println("base64Key = " + Encoders.BASE64.encode(secretKey.getEncoded()));

        Claims claims = Jwts.claims().setSubject(memberAuthInfo.getAccount());
        claims.put("roles", memberAuthInfo.getAuthorities());
        claims.put("extraInfo", memberAuthInfo.getExtraInfo());
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMilliSeconds))
                .signWith(secretKey)
                .compact();
    }

}
