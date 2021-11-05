package com.trip.hotel_gabriella.user.service.member;

import com.trip.hotel_gabriella.user.model.member.MemberAuthInfo;
import com.trip.hotel_gabriella.user.model.member.MemberLoginCommand;

public interface MemberAuthService {

    MemberAuthInfo memberAuthenticate(MemberLoginCommand memberLoginCommand); //아이디와 패스워드를 대조하여 참/거짓을 리턴

    void memberAuthSessionUp(); //회원 인증정보를 세션에 저장

    void memberAuthCookieSave();

    void login();

    void logout();

}
