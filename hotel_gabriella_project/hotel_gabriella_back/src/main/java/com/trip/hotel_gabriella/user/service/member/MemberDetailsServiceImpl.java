package com.trip.hotel_gabriella.user.service.member;

import com.trip.hotel_gabriella.common.security.UserAuthInfo;
import com.trip.hotel_gabriella.user.repository.MemberRepository;
import com.trip.hotel_gabriella.common.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberDetailsServiceImpl implements MemberDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserAuthInfo loadUserByUsername(String account) throws UsernameNotFoundException {

        Member findMember =
                memberRepository.findByAccount(account)
                        .orElseThrow(()->new UsernameNotFoundException("Account Not Found"));
        

        UserAuthInfo userAuthInfo
                = new UserAuthInfo(findMember.getAccount(), findMember.getPassword());
        userAuthInfo.setRoles("MEMBER");

        userAuthInfo.setExtraInfo("id", findMember.getId());

        System.out.println(userAuthInfo.getAccount());
        System.out.println("실제 멤버 로그인 시 여기를 찍는 지 궁금합니다.");

        return userAuthInfo;
    }

}
