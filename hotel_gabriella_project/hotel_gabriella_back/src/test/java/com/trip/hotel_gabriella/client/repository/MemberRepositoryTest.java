package com.trip.hotel_gabriella.client.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    private EntityManager em;

//    @Test
//    @DisplayName("유효성 검사가 작동하는지 확인")
//    public void memberValidation() {
//        Throwable thrown = Assertions.catchThrowable(()->{
//            memberJoinService.registerMember(memberRegisterRequest);
//        });
//
//        assertThat(thrown.getClass()).isInstanceOf(ConstraintViolationException.class);
//    }

}