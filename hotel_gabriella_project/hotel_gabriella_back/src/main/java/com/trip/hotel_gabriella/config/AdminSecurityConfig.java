package com.trip.hotel_gabriella.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableWebSecurity(debug = true) //@Configuration 포함함
//@RequiredArgsConstructor
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {


//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.antMatcher("/admin/**")
//                .authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .and()
//                .exceptionHandling()
//                .accessDeniedHandler(new CustomAccessDeniedHandler())
//                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());;
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authProvider());
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password(passwordEncoder().encode("adminPass"))
//                .roles("ADMIN");
//    }
}
