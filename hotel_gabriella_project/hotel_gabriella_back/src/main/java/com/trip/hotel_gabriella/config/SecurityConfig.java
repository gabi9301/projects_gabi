package com.trip.hotel_gabriella.config;

import com.trip.hotel_gabriella.common.exception.handler.CustomAccessDeniedHandler;
import com.trip.hotel_gabriella.common.exception.handler.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@EnableWebSecurity //@Configuration 포함함
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests() //httpServletRequest 에 접근해 건드리겠다.
                    .antMatchers("/","/join**","/login**","/logout**").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/member/**").hasRole("MEMBER")
                .and()
                    .exceptionHandling()
                        .accessDeniedHandler(((request, response, accessDeniedException) -> {
                            accessDeniedHandler().handle(request,response,accessDeniedException);
                        }))
                        .authenticationEntryPoint((request, response, authException) -> {
                            authenticationEntryPoint().commence(request,response,authException);
                        });

        httpSecurity.formLogin().loginPage("/login");




        httpSecurity.csrf().disable();
//        httpSecurity.csrf()
//                .ignoringAntMatchers()
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("adminPass"))
                .roles("ADMIN");
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }



}
