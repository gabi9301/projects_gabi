package com.trip.hotel_gabriella.admin.repository;

import com.trip.hotel_gabriella.common.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

//    void save(Room room);
//
//    Room findById(Long id);
//
//    List<Room> findAll();
}
