package com.trip.hotel_gabriella.config;
import com.trip.hotel_gabriella.admin.repository.JpaRoomRepository;
import com.trip.hotel_gabriella.admin.repository.MemoryRoomRepository;
import com.trip.hotel_gabriella.admin.repository.RoomRepository;
import com.trip.hotel_gabriella.admin.service.RegisterService;
import com.trip.hotel_gabriella.admin.service.RegisterServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

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
    public RegisterService registerService(){
        return new RegisterServiceImpl(roomRepository());
    }

}
