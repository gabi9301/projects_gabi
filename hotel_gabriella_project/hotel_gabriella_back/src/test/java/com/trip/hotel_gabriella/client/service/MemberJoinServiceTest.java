package com.trip.hotel_gabriella.client.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trip.hotel_gabriella.client.model.member.MemberRegisterRequest;
import com.trip.hotel_gabriella.client.model.member.MemberRegisterResponse;
import com.trip.hotel_gabriella.client.model.terms.TermsRegisterRequest;
import com.trip.hotel_gabriella.client.service.member.MemberJoinService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberJoinServiceTest{

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private MemberJoinService memberJoinService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final String FILE_PATH = "src/test/resources/test-json/member-join.json";

    private MemberRegisterRequest memberRegisterRequest;
    private List<TermsRegisterRequest> termsRegisterRequestList;

    @BeforeEach
    public void setup() throws IOException {
        //given
        memberRegisterRequest
                = objectMapper.readValue(new File(FILE_PATH), MemberRegisterRequest.class);

        termsRegisterRequestList = objectMapper.readValue(new File(FILE_PATH), List.class);
    }

    @Test
    @DisplayName("유일한 아이디인지 확인(최초 입력된 아이디)")
    public void checkUniqueAccount() {

        //when
        boolean result = memberJoinService.checkUniqueAccount(memberRegisterRequest.getAccount());

        //then
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("패스워드가 잘 암호화되었는지 확인")
    public void encodePassword() {

        //when
        String encodedPassword = memberJoinService.encodePassword(memberRegisterRequest.getPassword());

        //then
        Assertions.assertTrue(passwordEncoder.matches(memberRegisterRequest.getPassword(), encodedPassword));

    }
    @Test
    @DisplayName("새로운 회원 등록")
    public void registerMember() {
        MemberRegisterResponse result = memberJoinService.registerMember(memberRegisterRequest);
        
        Assertions.assertNotNull(result.getId());
    }
    
    @Test
    @DisplayName("동의항목이 제대로 들어왔는지 확인")
    public void readTerms() {
        for (Object item : termsRegisterRequestList) {
            TermsRegisterRequest request = (TermsRegisterRequest) item;
            System.out.println("item.getTermCode() = " + request.getTermCode());
            System.out.println("item.getAgreeYn() = " + request.getAgreeYn());
            System.out.println("item.getMember() = " + request.getMember());
        }
    }
}
