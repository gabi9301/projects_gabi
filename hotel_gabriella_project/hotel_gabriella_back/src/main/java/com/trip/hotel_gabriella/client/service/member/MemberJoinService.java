package com.trip.hotel_gabriella.client.service.member;

import com.trip.hotel_gabriella.client.model.member.MemberJoinRequest;
import com.trip.hotel_gabriella.client.model.member.MemberRegisterRequest;
import com.trip.hotel_gabriella.client.model.member.MemberRegisterResponse;

import javax.validation.Valid;


public interface MemberJoinService {

    boolean checkUniqueAccount(String accountCandidate);

    String encodePassword(String passwordCandidate);

    MemberRegisterResponse registerMember(@Valid MemberRegisterRequest memberRegisterRequest);

    void signInMember(MemberJoinRequest memberJoinRequest);

}
