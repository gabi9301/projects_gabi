package com.trip.hotel_gabriella.common.model;

import com.trip.hotel_gabriella.common.domain.Member;
import com.trip.hotel_gabriella.common.domain.Reservation;
import com.trip.hotel_gabriella.common.interfaces.model.GenericResponseEntityAdapter;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.Getter;

@Getter
public class ReservationInfo extends BaseDTO implements GenericResponseEntityAdapter<Reservation> {
    private Long id;
    private String checkIn;
    private String checkOut;
    private String name;
    private String phone;
    private int capacity;
    private boolean isMember;
    private Member member;

    @Override
    public ReservationInfo fromEntity(Reservation reservation) {
        this.id = reservation.getId();
        this.checkIn = reservation.getCheckIn().toString();
        this.checkOut = reservation.getCheckOut().toString();
        this.name = reservation.getName();
        this.phone = reservation.getPhone();
        this.capacity = reservation.getCapacity();
        this.isMember = reservation.getIsMember();
        this.member = reservation.getMember();

        return this;
    }
}
