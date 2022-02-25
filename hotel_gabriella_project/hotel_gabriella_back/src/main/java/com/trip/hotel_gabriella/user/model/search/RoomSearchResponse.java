package com.trip.hotel_gabriella.user.model.search;

import com.trip.hotel_gabriella.common.domain.BaseEntity;
import com.trip.hotel_gabriella.common.domain.Room;
import com.trip.hotel_gabriella.common.interfaces.model.GenericHttpResponseTransferor;
import com.trip.hotel_gabriella.common.interfaces.model.GenericResponseEntityAdapter;
import com.trip.hotel_gabriella.common.model.RoomInfo;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RoomSearchResponse extends BaseDTO implements GenericHttpResponseTransferor {

    private List<RoomInfo> suitableRooms;


}
