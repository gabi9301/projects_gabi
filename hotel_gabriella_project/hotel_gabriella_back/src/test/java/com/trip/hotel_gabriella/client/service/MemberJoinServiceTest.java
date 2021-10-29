package com.trip.hotel_gabriella.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trip.hotel_gabriella.client.model.member.MemberJoinRequest;
import com.trip.hotel_gabriella.client.model.member.MemberRegisterRequest;
import com.trip.hotel_gabriella.client.model.member.MemberRegisterResponse;
import com.trip.hotel_gabriella.client.model.terms.TermsRegisterRequest;
import com.trip.hotel_gabriella.client.service.member.MemberJoinService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;
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

       // objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        //given

        MemberJoinRequest memberJoinRequest = objectMapper.readValue(new File(FILE_PATH), MemberJoinRequest.class);
        memberRegisterRequest = memberJoinRequest.getMemberRegisterRequest();
        terms = memberJoinRequest.getTerms();

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

        //then
        assertThat((passwordEncoder.matches(memberRegisterRequest.getPassword(), encodedPassword))).isTrue();

    }
    @Test
    @DisplayName("새로운 회원 등록")
    public void registerMember() {
        //whem
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
        Throwable thrown = Assertions.catchThrowable(()->{
            memberJoinService.registerMember(memberRegisterRequest);
            em.flush();
        });

        assertThat(thrown.getClass()).isEqualTo(ConstraintViolationException.class);
    }


}
