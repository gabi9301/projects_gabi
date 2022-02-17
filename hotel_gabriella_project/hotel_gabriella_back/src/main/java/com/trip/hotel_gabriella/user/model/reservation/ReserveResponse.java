package com.trip.hotel_gabriella.user.model.reservation;

import com.trip.hotel_gabriella.common.domain.Reservation;
import com.trip.hotel_gabriella.common.interfaces.model.GenericResponseEntityAdapter;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.Getter;

@Getter
public class ReserveResponse extends BaseDTO implements GenericResponseEntityAdapter<Reservation> {

    private Long id;

    @Override
    public ReserveResponse fromEntity(Reservation reservation) {
        this.id = reservation.getId();
        return this;
    }
}
