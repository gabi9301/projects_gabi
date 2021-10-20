package com.trip.hotel_gabriella.client.model;

import com.trip.hotel_gabriella.common.domain.BaseEntity;

public interface GenericResponse<E extends BaseEntity,D extends BaseDto> {

    public D toDto(E entity);
}
