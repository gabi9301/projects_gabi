package com.trip.hotel_gabriella.client.model;

import com.trip.hotel_gabriella.common.domain.BaseEntity;

public interface GenericRequest<D extends BaseDto, E extends BaseEntity> {

    public E toEntity(D dto);
}
