package com.trip.hotel_gabriella.client.repository;

import com.trip.hotel_gabriella.common.domain.TermsHistory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional(rollbackFor = Exception.class)
public class JpaTermsRepository implements TermsRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(TermsHistory termsHistory) {
        em.persist(termsHistory);
    }
}
