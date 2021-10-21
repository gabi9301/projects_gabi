package com.trip.hotel_gabriella.client.model.member;

import com.trip.hotel_gabriella.common.domain.BaseEntity;

public interface GenericRequestEntityAdapter<E extends BaseEntity> {

    public E toEntity();
}
