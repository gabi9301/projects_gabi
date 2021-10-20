package com.trip.hotel_gabriella.admin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trip.hotel_gabriella.admin.model.RoomDetails;
import com.trip.hotel_gabriella.admin.model.RoomRegisterRequest;
import com.trip.hotel_gabriella.admin.model.RoomRegisterResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class RoomManageServiceTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private RoomManageService roomManageService;


    @Test
    @DisplayName("새로운 방 등록")
    public void saveRoom() throws Exception {
        System.out.println("objectMapper.toString() = " + objectMapper.toString());
        //given
        String filePath = "src/test/resources/test-json/room-manage.json";

        RoomRegisterRequest roomRegisterRequest
                = objectMapper.readValue(new File(filePath),RoomRegisterRequest.class);

        //when
        RoomRegisterResponse result = roomManageService.saveRoom(roomRegisterRequest);

        //then
        Assertions.assertNotNull(result.getId());
    }

    @Test
    @DisplayName("방 목록 전체보기")
    public void readAllRooms() {
        List<RoomDetails> allRooms = roomManageService.readAllRooms();

    }

}
