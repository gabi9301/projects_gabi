package com.trip.hotel_gabriella.common.security;

import com.trip.hotel_gabriella.user.model.member.MemberAuthInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService memberDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String account = authentication.getName();
        String password = authentication.getCredentials().toString();

        MemberAuthInfo memberAuthInfo = (MemberAuthInfo) memberDetailsService.loadUserByUsername(account);

        if(!passwordEncoder.matches(password,memberAuthInfo.getPassword())){
            throw new BadCredentialsException(account);
        }
        return new CustomAuthenticationToken(account,password,memberAuthInfo.getAuthorities(),memberAuthInfo.getExtraInfo());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
        //return CustomAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
