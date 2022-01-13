package com.trip.hotel_gabriella.admin.repository;

import com.trip.hotel_gabriella.common.domain.Room;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

@Repository
@Transactional(rollbackOn = Exception.class)
public class JpaRoomRepository{

    @PersistenceContext //이 시점에서 이미 @PersistenceContext가 프록시 em을 반환하도록 해서
    private EntityManager em; //해당 em의 주소는 항상 동일한 프록시 객체를 가리키지만
                                    //그 내부에서 참조하는 실제 em 객체는 매번 다르게 생성됨.
                                    //그러므로 final이 없어도 아무 차이가 없다.
                                    //프록시의 주소는 어찌되었든 바뀌지 않기 때문이다.
 //   @Override
    public void save(Room room) {
        em.persist(room);
    }

 //   @Override
    public Room findById(Long id) {
        return em.find(Room.class, id);
    }

 //   @Override
    public List<Room> findAll() {
        String query = "SELECT r FROM Room r";
        return em.createQuery(query, Room.class)
                .getResultList();
    }
}
