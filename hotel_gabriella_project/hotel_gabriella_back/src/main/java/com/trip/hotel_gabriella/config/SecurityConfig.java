package com.trip.hotel_gabriella.config;

import com.trip.hotel_gabriella.admin.repository.AdminRepository;
import com.trip.hotel_gabriella.common.interfaces.service.RedisService;
import com.trip.hotel_gabriella.common.security.*;
import com.trip.hotel_gabriella.user.repository.MemberRepository;
import com.trip.hotel_gabriella.user.service.admin.AdminDetailsService;
import com.trip.hotel_gabriella.user.service.admin.AdminDetailsServiceImpl;
import com.trip.hotel_gabriella.user.service.member.MemberDetailsService;
import com.trip.hotel_gabriella.user.service.member.MemberDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity(debug = true) //@Configuration 포함함
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberRepository memberRepository;
    private final AdminRepository adminRepository;
    private final RedisService redisService;
    //private final JwtTokenProvider jwtTokenProvider;



    //토큰 인증 방식--------------------------------------------------

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/member/**").hasRole("MEMBER")
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider(),redisService),
                        UsernamePasswordAuthenticationFilter.class);
    }





    @Bean
    public AdminDetailsService adminDetailsService() {
        return new AdminDetailsServiceImpl(adminRepository);
    }




//    @Bean
//    public JwtTokenProvider jwtTokenProvider_member() {
//        return new JwtTokenProvider(memberDetailsService(),redisAuthService);
//    }
//
//    @Bean
//    public JwtTokenProvider jwtTokenProvider_admin() {
//        return new JwtTokenProvider(adminDetailsService(),redisAuthService);
//    }




    //이하 서버 세션 방식---------------------------------------------------------------------
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity.authorizeRequests()//요청에 대한 권한을 지정할 수 있다.
//                .antMatchers("/","/join**","/login").permitAll();
//
//        httpSecurity
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/login.do")
//                .usernameParameter("account")
//                .passwordParameter("password")
//                .successHandler(new MemberLoginSuccessHandler())
//                .failureUrl("/")
//                .and()
//                .logout()
//                .logoutUrl("/logout.do")
//                .logoutSuccessUrl("/");
//
//        httpSecurity.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .invalidSessionUrl("/login")
//                .maximumSessions(1);
//
//        httpSecurity.csrf().disable();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authProvider());
//        auth.inMemoryAuthentication()
//                .withUser("admin912")
//                .password(passwordEncoder().encode("passWord32@"))
//                .roles("ADMIN");
//    }
//
//
//    @Configuration
//    @Order(1)
//    public static class MemberSecurityConfig extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity httpSecurity) throws Exception {
//            httpSecurity.antMatcher("/user/member/**")
//                    .authorizeRequests()
//                    .antMatchers("/user/member/**").hasRole("MEMBER")
//                    .anyRequest().authenticated()
//                    .and()
//                    .exceptionHandling()
//                    .accessDeniedHandler(new CustomAccessDeniedHandler())
//                    .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
//
//
//        }
//    }
//
//    @Configuration
//    @Order(2)
//    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity httpSecurity) throws Exception {
//            httpSecurity.antMatcher("/admin/**")
//                    .authorizeRequests()
//                    .antMatchers("/admin/**").hasRole("ADMIN")
//                    .and()
//                    .exceptionHandling()
//                    .accessDeniedHandler(new CustomAccessDeniedHandler())
//                    .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
//        }
//    }
//
//    @Bean
//    public AuthenticationProvider authProvider() {
//        return new CustomAuthenticationProvider(memberDetailsService(),passwordEncoder(),null);
//    }


    //언제나 주석이 풀려있어야 하는 구간(아래)

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public MemberDetailsService memberDetailsService() {
        return new MemberDetailsServiceImpl(memberRepository);
    }

    @Bean
    public JwtTokenProvider jwtTokenProvider(){
        return new JwtTokenProvider(memberDetailsService(),adminDetailsService(),redisService);
    }

    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor(jwtTokenProvider(),redisService);
    }



}