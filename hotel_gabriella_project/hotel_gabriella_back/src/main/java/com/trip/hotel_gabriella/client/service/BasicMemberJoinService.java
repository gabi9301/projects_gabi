package com.trip.hotel_gabriella.client.service;

import com.trip.hotel_gabriella.client.model.MemberJoinRequest;
import com.trip.hotel_gabriella.client.model.MemberJoinResponse;
import com.trip.hotel_gabriella.client.repository.MemberRepository;
import com.trip.hotel_gabriella.common.domain.Member;
import com.trip.hotel_gabriella.common.mappers.MemberJoinMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Component
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasicMemberJoinService implements MemberJoinService{

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
    public MemberJoinResponse registerMember(MemberJoinRequest memberJoinRequest) {
        MemberJoinResponse result = null;
        if(checkUniqueAccount(memberJoinRequest.getAccount())) {

            memberJoinRequest.setEncodedPassword(
                    passwordEncoder.encode(memberJoinRequest.getPassword()));

            Member member = (Member) memberJoinRequest.toEntity(memberJoinRequest);
            memberRepository.save(member);

            MemberJoinResponse memberJoinResponse
                    = (MemberJoinResponse) new MemberJoinResponse().toDto(member);

            result = memberJoinResponse;
        }
        return result;
    }
}
