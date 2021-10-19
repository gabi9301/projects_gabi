package com.trip.hotel_gabriella.client.repository;

import com.trip.hotel_gabriella.common.domain.Member;

public interface MemberRepository {

    public Long findCountByAccount(String account);

    public void save(Member member);

}
