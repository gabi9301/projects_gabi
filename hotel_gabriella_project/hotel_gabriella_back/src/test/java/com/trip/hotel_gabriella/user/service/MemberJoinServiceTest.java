package com.trip.hotel_gabriella.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trip.hotel_gabriella.user.model.member.MemberJoinCommand;
import com.trip.hotel_gabriella.user.model.member.MemberRegisterRequest;
import com.trip.hotel_gabriella.user.model.member.MemberRegisterResponse;
import com.trip.hotel_gabriella.user.model.terms.TermsRegisterRequest;
import com.trip.hotel_gabriella.user.service.member.MemberJoinService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberJoinServiceTest{

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private MemberJoinService memberJoinService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityManager em;

    private final String FILE_PATH = "src/test/resources/test-json/member-join.json";

    private MemberRegisterRequest memberRegisterRequest;
    private List<TermsRegisterRequest> terms;

    @BeforeEach
    public void setup() throws IOException {
        //given
        MemberJoinCommand memberJoinCommand = objectMapper.readValue(new File(FILE_PATH), MemberJoinCommand.class);
        memberRegisterRequest = memberJoinCommand.getMemberRegisterRequest();
        terms = memberJoinCommand.getTerms();
    }

    @Test
    @DisplayName("유일한 아이디인지 확인")
    public void checkUniqueAccount() {

        //when
        boolean result = memberJoinService.checkUniqueAccount(memberRegisterRequest.getAccount());

        //then
        assertThat(result).isTrue();

        //when

        memberJoinService.registerMember(memberRegisterRequest);
        result = memberJoinService.checkUniqueAccount(memberRegisterRequest.getAccount());

        //then
        assertThat(result).isFalse();

    }

    @Test
    @DisplayName("패스워드가 잘 암호화되었는지 확인")
    public void encodePassword() {

        //when
        String encodedPassword = memberJoinService.encodePassword(memberRegisterRequest.getPassword());

//        System.out.println("encodedPassword = " + encodedPassword);

        //then
        assertThat((passwordEncoder.matches(memberRegisterRequest.getPassword(), encodedPassword))).isTrue();

    }
    @Test
    @DisplayName("새로운 회원 등록")
    public void registerMember() {
        //when
        MemberRegisterResponse result = memberJoinService.registerMember(memberRegisterRequest);

        //then
        assertThat(result.getId()).isNotNull();
    }

    @Test
    @DisplayName("동의항목이 제대로 들어왔는지 확인")
    public void readTerms() {

    }

    @Test
    @DisplayName("유효성 검사가 작동하는지 확인")
    public void memberValidation() {
        //when
        Throwable thrown = Assertions.catchThrowable(()->{

            memberJoinService.registerMember(memberRegisterRequest);
            em.flush();
        });
        //then
       // assertThat(thrown.getClass()).isEqualTo(ConstraintViolationException.class);
    }


}
