package com.trip.hotel_gabriella.user.repository;

import com.trip.hotel_gabriella.common.domain.Member;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional(rollbackOn = Exception.class)
public class JpaMemberRepository {

    @PersistenceContext
    private EntityManager em;


 //   @Override
    public Long findCountByAccount(String account) {
        String query = "SELECT COUNT(m.account) FROM Member m WHERE m.account = :account";

        return em.createQuery(query, Long.class)
                .setParameter("account", account)
                .getSingleResult();
    }

 //   @Override
    public void save(Member member) {
        em.persist(member);
    }
}
