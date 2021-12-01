package com.trip.hotel_gabriella.common.security;

import com.trip.hotel_gabriella.user.service.member.MemberDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider { //JWT 토큰의 생성과 유효성 검사를 책임진다.

    @Value("spring.jwt.secret")
    private String SECRET_KEY;

    private final long tokenValidMilliseconds = 1000L * 60 * 60;

    private final MemberDetailsService memberDetailsService;

    @PostConstruct
    protected void init(){
        SECRET_KEY= Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

    public String createToken(String account, Collection<? extends GrantedAuthority> roles){
        Claims claims = Jwts.claims().setSubject(account);
        claims.put("roles", roles);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMilliseconds))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

}
