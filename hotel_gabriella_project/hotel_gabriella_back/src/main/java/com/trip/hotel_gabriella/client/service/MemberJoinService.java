package com.trip.hotel_gabriella.client.service;

import com.trip.hotel_gabriella.common.domain.Member;

public interface MemberJoinService {

    public boolean checkUniqueAccount(String accountCandidate);



    public void registerMember(Member member);

}
