package com.trip.hotel_gabriella.client.service;

import com.trip.hotel_gabriella.client.model.MemberJoinRequest;
import com.trip.hotel_gabriella.client.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
@RequiredArgsConstructor
public class BasicMemberJoinService implements MemberJoinService{

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public boolean checkUniqueAccount(String accountCandidate) {
        boolean result = false;
        int count = memberRepository.findCountByAccount(accountCandidate);
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

    @Override
    public void registerMember(MemberJoinRequest memberJoinRequest) {
        if(checkUniqueAccount(memberJoinRequest.getAccount())) {
            memberJoinRequest.setEncodedPassword(passwordEncoder.encode(memberJoinRequest.getPassword()));
            memberRepository.save(memberJoinRequest.toMemberEntity());
        }
    }
}
