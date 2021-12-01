package com.trip.hotel_gabriella.user.service.member;

import com.trip.hotel_gabriella.user.model.member.MemberAuthInfo;
import com.trip.hotel_gabriella.user.repository.MemberRepository;
import com.trip.hotel_gabriella.common.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberDetailsServiceImpl implements MemberDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public MemberAuthInfo loadUserByUsername(String account) throws UsernameNotFoundException {

        Member findMember =
                memberRepository.findByAccount(account)
                        .orElseThrow(()->new UsernameNotFoundException("Account Not Found"));
        

        MemberAuthInfo memberAuthInfo
                = new MemberAuthInfo(findMember.getAccount(), findMember.getPassword());
        memberAuthInfo.setRoles("MEMBER");

        memberAuthInfo.setExtraInfo("id", findMember.getId());

        System.out.println(memberAuthInfo.getAccount());

        return memberAuthInfo;
    }

}
