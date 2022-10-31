package com.trip.hotel_gabriella.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trip.hotel_gabriella.common.domain.ViewType;
import com.trip.hotel_gabriella.user.model.reservation.BookingCommand;
import com.trip.hotel_gabriella.user.model.reservation.ReserveRequest;
import com.trip.hotel_gabriella.user.service.reservation.BookingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestBookControllerTest {


    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    public void bookingTest() throws Exception {
        //given

        ReserveRequest reserveRequest =
                ReserveRequest.builder()
                        .checkIn("20220221")
                        .checkOut("20220223")
                        .name("Yujin")
                        .phone("01093920423")
                        .capacity(3)
                        .isMember(false)
                        .build();

        BookingCommand bookingCommand
                = BookingCommand.builder()
                .reserveRequest(reserveRequest)
                .roomId(3L)
                .viewType(ViewType.OCEAN)
                .build();

        //when
        String content = objectMapper.writeValueAsString(bookingCommand);

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/book.do")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated());

    }

}