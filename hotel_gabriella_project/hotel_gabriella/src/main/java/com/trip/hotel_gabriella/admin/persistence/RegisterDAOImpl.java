package com.trip.hotel_gabriella.admin.persistence;

import com.trip.hotel_gabriella.admin.domain.Room;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;


@Repository
public class RegisterDAOImpl implements RegisterDAO{
    @Override
    public Room insertRoom(MultiValueMap<String, Object> valueMap) {
        return null;
    }
//    @Override
//    public Room insertRoom(MultiValueMap<String, Object> valueMap) {
//
//
//        return room;
//    }
}
