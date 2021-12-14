package com.trip.hotel_gabriella.config;

import com.trip.hotel_gabriella.common.exception.handler.CustomAccessDeniedHandler;
import com.trip.hotel_gabriella.common.exception.handler.CustomAuthenticationEntryPoint;
import com.trip.hotel_gabriella.common.security.CustomAuthenticationProvider;
import com.trip.hotel_gabriella.common.security.JwtTokenProvider;
import com.trip.hotel_gabriella.common.security.MemberLoginSuccessHandler;
import com.trip.hotel_gabriella.user.repository.MemberRepository;
import com.trip.hotel_gabriella.user.service.member.MemberDetailsService;
import com.trip.hotel_gabriella.user.service.member.MemberDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity(debug = true) //@Configuration 포함함
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final MemberRepository memberRepository;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests()//요청에 대한 권한을 지정할 수 있다.
                .antMatchers("/","/join**","/login").permitAll();

        httpSecurity
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login.do")
                .usernameParameter("account")
                .passwordParameter("password")
                .successHandler(new MemberLoginSuccessHandler())
                .failureUrl("/")
                .and()
                .logout()
                .logoutUrl("/logout.do")
                .logoutSuccessUrl("/login");

        httpSecurity.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .invalidSessionUrl("/login")
                .maximumSessions(1);

        httpSecurity.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("adminPass"))
                .roles("ADMIN");
    }


    @Configuration
    @Order(1)
    public static class MemberSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.antMatcher("/user/member/**")
                    .authorizeRequests()
                    .antMatchers("/member/**").hasRole("MEMBER")
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling()
                    .accessDeniedHandler(new CustomAccessDeniedHandler())
                    .authenticationEntryPoint(new CustomAuthenticationEntryPoint());


        }
    }

    @Configuration
    @Order(2)
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.antMatcher("/admin/**")
                    .authorizeRequests()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .and()
                    .exceptionHandling()
                    .accessDeniedHandler(new CustomAccessDeniedHandler())
                    .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    public MemberDetailsService memberDetailsService(){
        return new MemberDetailsServiceImpl(memberRepository);
    }

    @Bean
    public AuthenticationProvider authProvider() {
        return new CustomAuthenticationProvider(memberDetailsService(),passwordEncoder());
    }




}