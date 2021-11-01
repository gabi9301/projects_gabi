package com.trip.hotel_gabriella.client.repository;

import com.trip.hotel_gabriella.common.domain.Member;

public interface MemberRepository {

    Long findCountByAccount(String account);

    void save(Member member);

}
