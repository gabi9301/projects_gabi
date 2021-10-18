package com.trip.hotel_gabriella.client.service;


import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberJoinServiceTest {

    @Test
    @DisplayName("유일한 아이디인지 확인하기")
    public void checkUniqueAccount() {
        //given


        //when
        //then
    }
}
