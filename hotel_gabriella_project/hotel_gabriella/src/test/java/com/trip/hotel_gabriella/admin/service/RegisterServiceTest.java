package com.trip.hotel_gabriella.admin.service;

import com.trip.hotel_gabriella.admin.domain.Room;
import org.assertj.core.api.Assertions;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class RegisterServiceTest {

    @Autowired
    private RegisterService registerService;


    @Test
    public void saveRoom() {
        //given
        Room room = new Room(301, 3, "DOUBLE", "NONE");

        //when
        registerService.saveRoom(room);

        Room findRoom = registerService.readRoom(room.getId());

        //then
        Assertions.assertThat(room).isEqualTo(findRoom);
    }

    @Test
    public void readAllRooms() {
        List<Room> allRooms = registerService.readAllRooms();

    }

}
