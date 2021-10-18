package com.trip.hotel_gabriella.client.service;

import com.trip.hotel_gabriella.client.model.MemberJoinRequest;


public interface MemberJoinService {

    public boolean checkUniqueAccount(String accountCandidate);

    public String encodePassword(String passwordCandidate);

    public void registerMember(MemberJoinRequest memberJoinRequest);

}
