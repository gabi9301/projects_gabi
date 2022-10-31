package com.trip.hotel_gabriella.user.service.member;

import lombok.RequiredArgsConstructor;

import com.trip.hotel_gabriella.user.model.member.MemberJoinCommand;
import com.trip.hotel_gabriella.user.model.member.MemberRegisterRequest;
import com.trip.hotel_gabriella.user.model.member.MemberRegisterResponse;
import com.trip.hotel_gabriella.user.model.terms.TermsRegisterRequest;
import com.trip.hotel_gabriella.user.repository.MemberRepository;
import com.trip.hotel_gabriella.user.service.terms.TermsManageService;
import com.trip.hotel_gabriella.common.domain.Member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberJoinServiceImpl implements MemberJoinService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;  //@prePersist로 빼는 걸 고려

    private final TermsManageService termsManageService;

    @Transactional
    public boolean checkUniqueAccount(String accountCandidate) {
        Long count = memberRepository.countByAccountEquals(accountCandidate);
        return count == 0;

    }

    @Override
    public String encodePassword(String passwordCandidate) {

        return passwordEncoder.encode(passwordCandidate);
    }

    @Transactional
    public MemberRegisterResponse registerMember(MemberRegisterRequest memberRegisterRequest) {

        if(checkUniqueAccount(memberRegisterRequest.getAccount())) {

            memberRegisterRequest.setEncodedPassword(
                    passwordEncoder.encode(memberRegisterRequest.getPassword()));

            Member member = memberRegisterRequest.toEntity();
            memberRepository.save(member);

            return new MemberRegisterResponse().fromEntity(member);
        }else{
            log.info("계정 아이디 중복, 에러 핸들러 필요");
            return null;
        }
    }

    @Transactional
    public void signInMember(MemberJoinCommand memberJoinCommand) {

        MemberRegisterRequest memberRegisterRequest = memberJoinCommand.getMemberRegisterRequest();
        List<TermsRegisterRequest> termsRegisterRequestList = memberJoinCommand.getTerms();
        
        MemberRegisterResponse memberRegisterResponse = registerMember(memberRegisterRequest);

        if(memberRegisterResponse != null) {
            Member newMember = Member.builder().id(memberRegisterResponse.getId()).build();
            termsManageService.processTerms(termsRegisterRequestList,newMember);
        }
    }
}
