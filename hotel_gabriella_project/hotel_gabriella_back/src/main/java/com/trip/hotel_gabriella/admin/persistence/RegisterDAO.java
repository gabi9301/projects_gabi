package com.trip.hotel_gabriella.admin.persistence;


import com.trip.hotel_gabriella.common.domain.Room;
import org.springframework.util.MultiValueMap;

public interface RegisterDAO {

    public Room insertRoom(MultiValueMap<String,Object> valueMap);




}
