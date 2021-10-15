package com.trip.hotel_gabriella.config;
import com.trip.hotel_gabriella.admin.repository.JpaRoomRepository;
import com.trip.hotel_gabriella.admin.repository.RoomRepository;
import com.trip.hotel_gabriella.admin.service.RoomManageService;
import com.trip.hotel_gabriella.admin.service.RoomManageServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
    public RoomManageService roomManageService(){
        return new RoomManageServiceImpl(roomRepository());
    }

}
