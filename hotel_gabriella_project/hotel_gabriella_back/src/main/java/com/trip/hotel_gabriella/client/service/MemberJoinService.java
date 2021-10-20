package com.trip.hotel_gabriella.client.service;

import com.trip.hotel_gabriella.client.model.MemberJoinRequest;
import com.trip.hotel_gabriella.client.model.MemberJoinResponse;


public interface MemberJoinService {

    public boolean checkUniqueAccount(String accountCandidate);

    public String encodePassword(String passwordCandidate);

    public MemberJoinResponse registerMember(MemberJoinRequest memberJoinRequest);

}
