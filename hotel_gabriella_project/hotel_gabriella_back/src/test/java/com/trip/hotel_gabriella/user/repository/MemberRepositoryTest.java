package com.trip.hotel_gabriella.user.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

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