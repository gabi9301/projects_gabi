package com.trip.hotel_gabriella.admin.repository;

import com.trip.hotel_gabriella.common.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
//    @Query("SELECT COUNT(a.account) FROM Admin a WHERE a.account = :account")
//    Long findCountByAccount(String account);

    //@Query("SELECT m FROM Member m WHERE m.account = :account")
    Optional<Admin> findByAccount(String account);

}
