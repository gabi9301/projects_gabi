package com.trip.hotel_gabriella.client.service.member;

import com.trip.hotel_gabriella.client.model.member.MemberJoinRequest;
import com.trip.hotel_gabriella.client.model.member.MemberJoinResponse;
import com.trip.hotel_gabriella.client.model.member.MemberRegisterRequest;
import com.trip.hotel_gabriella.client.model.member.MemberRegisterResponse;
import com.trip.hotel_gabriella.client.model.terms.TermsRegisterRequest;
import com.trip.hotel_gabriella.client.repository.MemberRepository;
import com.trip.hotel_gabriella.common.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasicMemberJoinService implements MemberJoinService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;


    @Transactional
    public boolean checkUniqueAccount(String accountCandidate) {
        boolean result = false;
        Long count = memberRepository.findCountByAccount(accountCandidate);
        if(count == 0){
            result = true;
        }
        return result;

    }

    @Override
    public String encodePassword(String passwordCandidate) {
        String result = passwordEncoder.encode(passwordCandidate);
        return result;
    }

    @Transactional
    public MemberRegisterResponse registerMember(MemberRegisterRequest memberRegisterRequest) {
        MemberRegisterResponse result = null;
        if(checkUniqueAccount(memberRegisterRequest.getAccount())) {

            memberRegisterRequest.setEncodedPassword(
                    passwordEncoder.encode(memberRegisterRequest.getPassword()));

            Member member = memberRegisterRequest.toEntity();
            memberRepository.save(member);

            result = new MemberRegisterResponse().fromEntity(member);
        }
        return result;
    }

    @Override
    public MemberJoinResponse joinMember(MemberJoinRequest memberJoinRequest) {
        MemberJoinResponse result = null;

        MemberRegisterRequest memberRegisterRequest = memberJoinRequest.getMemberRegisterRequest();
        TermsRegisterRequest termsRegisterRequest = memberJoinRequest.getTermsRegisterRequest();

        MemberRegisterResponse memberRegisterResponse = registerMember(memberRegisterRequest);



        return result;
    }
}
