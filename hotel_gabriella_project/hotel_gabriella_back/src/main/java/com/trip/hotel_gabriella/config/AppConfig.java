package com.trip.hotel_gabriella.config;

import com.trip.hotel_gabriella.admin.repository.RoomRepository;
import com.trip.hotel_gabriella.admin.service.room.RoomManageService;
import com.trip.hotel_gabriella.admin.service.room.RoomManageServiceImpl;
import com.trip.hotel_gabriella.user.repository.MemberRepository;
import com.trip.hotel_gabriella.user.repository.ReservationRepository;
import com.trip.hotel_gabriella.user.repository.ReservationRoomRepository;
import com.trip.hotel_gabriella.user.repository.TermsRepository;
import com.trip.hotel_gabriella.user.service.member.MemberJoinServiceImpl;
import com.trip.hotel_gabriella.user.service.member.MemberJoinService;
import com.trip.hotel_gabriella.user.service.reservation.*;
import com.trip.hotel_gabriella.user.service.terms.TermsManageServiceImpl;
import com.trip.hotel_gabriella.user.service.terms.TermsManageService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
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
    public ReserveService reserveService(ReservationRepository reservationRepository){
        return new ReserveServiceImpl(reservationRepository);
    }

    @Bean
    public RoomReserveService roomReserveService(ReservationRoomRepository reservationRoomRepository){
        return new RoomReserveServiceImpl(reservationRoomRepository);
    }

    @Bean
    public BookingService bookingService(ReservationRepository reservationRepository
            ,ReservationRoomRepository reservationRoomRepository
            ,RoomManageService roomManageService){

        return new BookingServiceImpl(reserveService(reservationRepository)
        ,roomReserveService(reservationRoomRepository)
        ,roomManageService);
    }


}
