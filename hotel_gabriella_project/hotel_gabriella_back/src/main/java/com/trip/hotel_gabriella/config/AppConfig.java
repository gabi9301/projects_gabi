package com.trip.hotel_gabriella.config;

import com.trip.hotel_gabriella.admin.repository.RoomRepository;
import com.trip.hotel_gabriella.admin.service.room.RoomManageService;
import com.trip.hotel_gabriella.admin.service.room.RoomManageServiceImpl;
import com.trip.hotel_gabriella.user.repository.MemberRepository;
import com.trip.hotel_gabriella.user.repository.TermsRepository;
import com.trip.hotel_gabriella.user.service.member.MemberAuthServiceImpl;
import com.trip.hotel_gabriella.user.service.member.MemberJoinServiceImpl;
import com.trip.hotel_gabriella.user.service.member.MemberAuthService;
import com.trip.hotel_gabriella.user.service.member.MemberJoinService;
import com.trip.hotel_gabriella.user.service.terms.TermsManageServiceImpl;
import com.trip.hotel_gabriella.user.service.terms.TermsManageService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    public RoomManageService roomManageService(RoomRepository roomRepository){
        return new RoomManageServiceImpl(roomRepository);
    }

    @Bean
    public MemberJoinService memberJoinService(
            MemberRepository memberRepository,PasswordEncoder passwordEncoder
            ,TermsRepository termsRepository) {
        return new MemberJoinServiceImpl(
                memberRepository,passwordEncoder,termsManageService(termsRepository));
    }

    @Bean
    public TermsManageService termsManageService(TermsRepository termsRepository) {
        return new TermsManageServiceImpl(termsRepository);
    }

    @Bean
    public MemberAuthService memberAuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder){
        return new MemberAuthServiceImpl(memberRepository,passwordEncoder);
    }









}
