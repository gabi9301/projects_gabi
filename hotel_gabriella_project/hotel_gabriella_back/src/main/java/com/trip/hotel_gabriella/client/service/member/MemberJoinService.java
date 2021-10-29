package com.trip.hotel_gabriella.client.service.member;

import com.trip.hotel_gabriella.client.model.member.MemberJoinRequest;
import com.trip.hotel_gabriella.client.model.member.MemberRegisterRequest;
import com.trip.hotel_gabriella.client.model.member.MemberRegisterResponse;


public interface MemberJoinService {

    public boolean checkUniqueAccount(String accountCandidate);

    public String encodePassword(String passwordCandidate);

    public MemberRegisterResponse registerMember(MemberRegisterRequest memberRegisterRequest);

    public void joinMember(MemberJoinRequest memberJoinRequest);

}
