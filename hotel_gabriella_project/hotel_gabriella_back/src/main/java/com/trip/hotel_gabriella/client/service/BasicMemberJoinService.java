package com.trip.hotel_gabriella.client.service;

import com.trip.hotel_gabriella.client.model.MemberJoinRequest;
import com.trip.hotel_gabriella.client.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    @Transactional
    public void registerMember(MemberJoinRequest memberJoinRequest) {
        if(checkUniqueAccount(memberJoinRequest.getAccount())) {
            memberJoinRequest.setEncodedPassword(
                    passwordEncoder.encode(memberJoinRequest.getPassword()));
            memberRepository.save(memberJoinRequest.toEntity());
        }
    }
}
