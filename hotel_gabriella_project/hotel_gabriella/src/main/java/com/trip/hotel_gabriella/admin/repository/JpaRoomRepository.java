package com.trip.hotel_gabriella.admin.repository;

import com.trip.hotel_gabriella.admin.domain.Room;
import com.trip.hotel_gabriella.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Component
@Repository
public class JpaRoomRepository implements RoomRepository{

    private final EntityManager em;

    public JpaRoomRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Room room) {
        em.persist(room);
    }

    @Override
    public Room findById(Long id) {
        Room findRoom = em.find(Room.class, id);
        return findRoom;
    }

    @Override
    public List<Room> findAll() {
        String query = "SELECT r FROM Room r";
        return em.createQuery(query, Room.class)
                .getResultList();
    }
}
