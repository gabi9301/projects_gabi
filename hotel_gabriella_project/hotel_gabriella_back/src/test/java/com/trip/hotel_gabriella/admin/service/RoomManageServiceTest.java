package com.trip.hotel_gabriella.admin.service;


import com.trip.hotel_gabriella.admin.model.RoomRegisterRequest;
import com.trip.hotel_gabriella.common.domain.Room;
import org.assertj.core.api.Assertions;


import org.junit.jupiter.api.DisplayName;
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
public class RoomManageServiceTest {

    @Autowired
    private RoomManageService roomManageService;


    @Test
    @DisplayName("새로운 방 등록하기")
    public void saveRoom() {
        //given
        RoomRegisterRequest roomRegisterRequest = new RoomRegisterRequest(
                301
                , 3
                , "DOUBLE"
                ,"NONE");

        //when
        Room room = roomManageService.saveRoom(roomRegisterRequest);

        Room findRoom = roomManageService.readRoom(room.getId());

        //then
        Assertions.assertThat(room).isEqualTo(findRoom);
    }

    @Test
    @DisplayName("방 목록 전체보기")
    public void readAllRooms() {
        List<Room> allRooms = roomManageService.readAllRooms();

    }

}
