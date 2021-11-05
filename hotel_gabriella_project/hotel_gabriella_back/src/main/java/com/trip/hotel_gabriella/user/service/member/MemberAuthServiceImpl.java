package com.trip.hotel_gabriella.user.service.member;

import com.trip.hotel_gabriella.user.model.member.MemberAuthInfo;
import com.trip.hotel_gabriella.user.model.member.MemberLoginCommand;
import com.trip.hotel_gabriella.user.repository.MemberRepository;
import com.trip.hotel_gabriella.common.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberAuthServiceImpl implements MemberAuthService{

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberAuthInfo memberAuthenticate(MemberLoginCommand memberLoginCommand) {
        String encodedPasswordInput
                = passwordEncoder.encode(memberLoginCommand.getPassword());

        Member findMember =
                memberRepository.findByAccount(memberLoginCommand.getAccount())
                .filter(m -> m.getPassword().equals(encodedPasswordInput))
                .orElse(null);

        if(findMember == null){
            //throws InvalidIdAndPasswordException
            return null;
        }else{
            return new MemberAuthInfo().fromEntity(findMember);
        }

    }

    @Override
    public void memberAuthSessionUp() {

    }

    @Override
    public void memberAuthCookieSave() {

    }

    @Override
    public void login() {

    }

    @Override
    public void logout() {

    }
}
