package com.trip.hotel_gabriella.common.interfaces.model;

import com.trip.hotel_gabriella.common.domain.BaseEntity;
import com.trip.hotel_gabriella.user.model.BaseDTO;

public interface GenericResponseEntityAdapter<E extends BaseEntity> {

    BaseDTO fromEntity(E entity);
}
