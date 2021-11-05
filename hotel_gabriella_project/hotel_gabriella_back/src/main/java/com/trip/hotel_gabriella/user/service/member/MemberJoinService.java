package com.trip.hotel_gabriella.user.service.member;

import com.trip.hotel_gabriella.user.model.member.MemberJoinCommand;
import com.trip.hotel_gabriella.user.model.member.MemberRegisterRequest;
import com.trip.hotel_gabriella.user.model.member.MemberRegisterResponse;

import javax.validation.Valid;


public interface MemberJoinService {

    boolean checkUniqueAccount(String accountCandidate);

    String encodePassword(String passwordCandidate);

    MemberRegisterResponse registerMember(@Valid MemberRegisterRequest memberRegisterRequest);

    void signInMember(MemberJoinCommand memberJoinCommand);

}
