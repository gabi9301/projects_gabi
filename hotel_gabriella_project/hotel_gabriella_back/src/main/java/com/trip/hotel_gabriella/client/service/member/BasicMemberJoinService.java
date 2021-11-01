package com.trip.hotel_gabriella.client.service.member;

import com.trip.hotel_gabriella.common.validation.validator.ValidationGroups;
import lombok.RequiredArgsConstructor;

import com.trip.hotel_gabriella.client.model.member.MemberJoinRequest;
import com.trip.hotel_gabriella.client.model.member.MemberRegisterRequest;
import com.trip.hotel_gabriella.client.model.member.MemberRegisterResponse;
import com.trip.hotel_gabriella.client.model.terms.TermsRegisterRequest;
import com.trip.hotel_gabriella.client.repository.MemberRepository;
import com.trip.hotel_gabriella.client.service.terms.TermsManageService;
import com.trip.hotel_gabriella.common.domain.Member;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasicMemberJoinService implements MemberJoinService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;  //@prePersist로 빼는 걸 고려

    private final TermsManageService termsManageService;


    @Transactional
    public boolean checkUniqueAccount(String accountCandidate) {
        Long count = memberRepository.findCountByAccount(accountCandidate);
        if(count == 0){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public String encodePassword(String passwordCandidate) {

        return passwordEncoder.encode(passwordCandidate);
    }

    @Transactional
    public MemberRegisterResponse registerMember(
            @Validated(ValidationGroups.validatePassword.class)
                    MemberRegisterRequest memberRegisterRequest) {

        if(checkUniqueAccount(memberRegisterRequest.getAccount())) {

            memberRegisterRequest.setEncodedPassword(
                    passwordEncoder.encode(memberRegisterRequest.getPassword()));

            Member member = memberRegisterRequest.toEntity();
            memberRepository.save(member);

            return new MemberRegisterResponse().fromEntity(member);
        }else{
            System.out.println("계정 아이디 중복, 에러 핸들러 필요");
            return null;
        }
    }

    @Transactional
    public void signInMember(MemberJoinRequest memberJoinRequest) {

        MemberRegisterRequest memberRegisterRequest = memberJoinRequest.getMemberRegisterRequest();
        List<TermsRegisterRequest> termsRegisterRequestList = memberJoinRequest.getTerms();

        MemberRegisterResponse memberRegisterResponse = registerMember(memberRegisterRequest);

        if(memberRegisterResponse != null) {
            Member newMember = Member.builder().id(memberRegisterResponse.getId()).build();
            termsManageService.processTerms(termsRegisterRequestList,newMember);
        }
    }
}
