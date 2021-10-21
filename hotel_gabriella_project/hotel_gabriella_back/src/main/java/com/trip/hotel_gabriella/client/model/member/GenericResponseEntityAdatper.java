package com.trip.hotel_gabriella.client.model.member;

import com.trip.hotel_gabriella.client.model.BaseDTO;
import com.trip.hotel_gabriella.common.domain.BaseEntity;

public interface GenericResponseEntityAdatper<E extends BaseEntity> {

    public BaseDTO fromEntity(E entity);
}
