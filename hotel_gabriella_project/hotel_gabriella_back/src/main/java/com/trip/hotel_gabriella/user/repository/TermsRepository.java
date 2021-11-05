package com.trip.hotel_gabriella.user.repository;

import com.trip.hotel_gabriella.common.domain.TermsHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsRepository extends JpaRepository<TermsHistory, Long> {

  //  void save(TermsHistory termsHistory);

}
