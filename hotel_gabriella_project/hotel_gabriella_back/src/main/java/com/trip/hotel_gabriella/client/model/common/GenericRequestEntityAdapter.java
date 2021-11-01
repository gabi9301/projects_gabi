package com.trip.hotel_gabriella.client.model.common;

import com.trip.hotel_gabriella.common.domain.BaseEntity;

public interface GenericRequestEntityAdapter<E extends BaseEntity> {

    E toEntity();
}
