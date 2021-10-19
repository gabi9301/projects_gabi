package com.trip.hotel_gabriella.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trip.hotel_gabriella.admin.repository.JpaRoomRepository;
import com.trip.hotel_gabriella.admin.repository.RoomRepository;
import com.trip.hotel_gabriella.admin.service.RoomManageService;
import com.trip.hotel_gabriella.admin.service.BasicRoomManageService;
import com.trip.hotel_gabriella.client.repository.JpaMemberRepository;
import com.trip.hotel_gabriella.client.repository.MemberRepository;
import com.trip.hotel_gabriella.client.service.BasicMemberJoinService;
import com.trip.hotel_gabriella.client.service.MemberJoinService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    public RoomRepository roomRepository(){
        return new JpaRoomRepository();
    }

//    @Bean
//    public RoomRepository roomRepository() {
//        return new MemoryRoomRepository();
//    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository();
    }

    @Bean
    public RoomManageService roomManageService(){
        return new BasicRoomManageService(roomRepository());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MemberJoinService memberJoinService() {
        return new BasicMemberJoinService(memberRepository(), passwordEncoder());
    }





}
