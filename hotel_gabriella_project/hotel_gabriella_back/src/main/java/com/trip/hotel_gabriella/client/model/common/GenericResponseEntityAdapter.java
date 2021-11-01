package com.trip.hotel_gabriella.client.model.common;

import com.trip.hotel_gabriella.client.model.BaseDTO;
import com.trip.hotel_gabriella.common.domain.BaseEntity;

public interface GenericResponseEntityAdapter<E extends BaseEntity> {

    BaseDTO fromEntity(E entity);
}
