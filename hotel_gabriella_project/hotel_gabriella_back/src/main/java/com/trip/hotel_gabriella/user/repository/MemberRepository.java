package com.trip.hotel_gabriella.user.repository;

import com.trip.hotel_gabriella.common.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

//    @Query("SELECT COUNT(m.account) FROM Member m WHERE m.account = :account")
//    Long findCountByAccount(String account);

    Long countByAccountEquals(String account);

    //@Query("SELECT m FROM Member m WHERE m.account = :account")
    Optional<Member> findByAccount(String account);

}
