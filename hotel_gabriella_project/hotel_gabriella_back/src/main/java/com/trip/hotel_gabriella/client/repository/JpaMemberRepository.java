package com.trip.hotel_gabriella.client.repository;

import com.trip.hotel_gabriella.common.domain.Member;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Repository
@Transactional(rollbackOn = Exception.class)
public class JpaMemberRepository implements MemberRepository{

    @PersistenceContext
    private EntityManager em;


    @Override
    public int findCountByAccount(String account) {
        String query = "SELECT COUNT(m.account) FROM Member m WHERE m.account = :account";

        return em.createQuery(query, Integer.class)
                .setParameter("account", account)
                .getSingleResult();
    }

    @Override
    public void save(Member member) {
        em.persist(member);
    }
}
