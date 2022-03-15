package com.trip.hotel_gabriella.common.security;

import com.trip.hotel_gabriella.admin.service.admin.AdminDetailsService;
import com.trip.hotel_gabriella.common.interfaces.service.RedisService;
import com.trip.hotel_gabriella.common.model.TokenReissueRequest;
import com.trip.hotel_gabriella.user.service.member.MemberDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;


@RequiredArgsConstructor
public class JwtTokenProvider { //JWT 토큰의 생성과 유효성 검사를 책임진다.

    private SecretKey secretKey;

    private String base64Key;

    @Value("${spring.jwt.expire.accessTokenValidMilliSeconds}")
    private long accessTokenValidMilliSeconds;

    @Value("${spring.jwt.expire.refreshTokenValidMilliSeconds}")
    private long refreshTokenValidMilliSeconds;

//    private final UserDetailsService userDetailsService;

    private final MemberDetailsService memberDetailsService;

    private final AdminDetailsService adminDetailsService;

    private final RedisService redisService;

    public Map<String, String> createToken(UserAuthInfo userAuthInfo) {   //토큰을 생성한다
        String accessToken = createAccessToken(userAuthInfo);
        String refreshToken = createRefreshToken(userAuthInfo);
        System.out.println("refreshTokenValidMilliSeconds = " + refreshTokenValidMilliSeconds);
        redisService.setData("RT_" + userAuthInfo.getAccount(), refreshToken, refreshTokenValidMilliSeconds);

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("id", (userAuthInfo.getExtraInfo().get("id").toString()));
        tokenMap.put("account", userAuthInfo.getAccount());
        tokenMap.put("accessToken", accessToken);
        tokenMap.put("refreshToken", refreshToken);

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
        Claims claims = Jwts.claims().setSubject(userAuthInfo.getAccount());

        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenValidMilliSeconds))
                .signWith(secretKey)
                .compact();

    }


    public Map<String, String> resolveToken(HttpServletRequest request) {
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("authorization", resolveAccessToken(request));
        tokenMap.put("refreshToken", resolveRefreshToken(request));
        return tokenMap;
    }

    public String resolveAccessToken(HttpServletRequest request) {
        return request.getHeader("authorization") != null ? request.getHeader("authorization") : null;
    }

    public String resolveRefreshToken(HttpServletRequest request) {
        return request.getHeader("refreshToken") != null ? request.getHeader("refreshToken") : null;
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

    public Authentication getAuthentication(String token, String serviceIdentifier) {
        UserDetails userDetails = null;
        if (serviceIdentifier.equals("member")) {
            userDetails = memberDetailsService.loadUserByUsername(this.getUserIdentifyKey(token));
        } else if (serviceIdentifier.equals("admin")) {
            userDetails = adminDetailsService.loadUserByUsername(this.getUserIdentifyKey(token));
        }

        assert userDetails != null;
        return new CustomAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public void setHeaderAccessToken(HttpServletResponse response, String accessToken) {
        response.setHeader("authorization", accessToken);
    }

    public void setHeaderRefreshToken(HttpServletResponse response, String refreshToken) {
        response.setHeader("refreshToken", refreshToken);
    }

    public Map<String, String> reissueToken(TokenReissueRequest tokenReissueRequest) {

        Map<String, String> tokenMap = null;
        Authentication authentication =
                this.getAuthentication(tokenReissueRequest.getTokenPayload().getRefreshToken()
                        , tokenReissueRequest.getDetailsServiceIdentifier());
        UserDetails userDetails = ((UserDetails) authentication.getPrincipal());

        if ((redisService.exist("RT_" + userDetails.getUsername())
                && (redisService.getData("RT_" + userDetails.getUsername())).equals(tokenReissueRequest.getTokenPayload().getRefreshToken()))) {

            UserAuthInfo userAuthInfo
                    = new UserAuthInfo(userDetails.getUsername(), userDetails.getPassword());
            tokenMap = this.createToken(userAuthInfo);

            System.out.println("newAccessToken = " + tokenMap.get("accessToken"));
            System.out.println("refreshToken = " + tokenMap.get("refreshToken"));
        }
        return tokenMap;
    }


    public long getExpirationTime(String accessToken) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(secretKey).build()
                .parseClaimsJws(accessToken)
                .getBody()
                .getExpiration();

        long now = new Date().getTime();

        return expiration.getTime() - now;


    }

    public void invalidateToken(String accessToken) {

        String account = this.getUserIdentifyKey(accessToken);
        if (this.validateToken(accessToken)) {
            long expiration = this.getExpirationTime(accessToken);
            redisService.setData(accessToken, "logout", expiration);
        }

        if (redisService.exist("RT_" + account)) {
            redisService.deleteData("RT_" + account);
        }
    }

    public boolean isLoggedOut(String accessKey){

        return redisService.exist(accessKey)
                && redisService.getData(accessKey).equals("logout");
    }


}
