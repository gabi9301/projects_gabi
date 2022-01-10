package com.trip.hotel_gabriella.common.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

//@Component  //서버 세션 방식으로 로그인 시 Bean으로 등록이 되어 있어야 작동한다
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    //서버 세션 방식으로 로그인 할 경우
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String account = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserAuthInfo userAuthInfo = (UserAuthInfo) userDetailsService.loadUserByUsername(account);

        if(!passwordEncoder.matches(password,userAuthInfo.getPassword())){
            throw new BadCredentialsException(account);
        }
        return new CustomAuthenticationToken(account,password,userAuthInfo.getAuthorities(),userAuthInfo.getExtraInfo());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
        //return CustomAuthenticationToken.class.isAssignableFrom(authentication);
    }

    //JWT 방식으로 로그인 할 경우

    public Map<String, String> authenticate(LoginCommand loginCommand) {
        String account = loginCommand.getAccount();
        String password = loginCommand.getPassword();

        UserAuthInfo userAuthInfo = (UserAuthInfo) userDetailsService.loadUserByUsername(account);

        if(!passwordEncoder.matches(password,userAuthInfo.getPassword())){
            throw new BadCredentialsException(account);
        }

        return jwtTokenProvider.createToken(userAuthInfo);
    }

    public Map<String,String > reissue(TokenReissueRequest tokenReissueRequest){
        return jwtTokenProvider.reissueToken(tokenReissueRequest);
    }


}
