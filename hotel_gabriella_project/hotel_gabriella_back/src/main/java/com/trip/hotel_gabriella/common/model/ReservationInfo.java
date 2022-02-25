package com.trip.hotel_gabriella.common.model;

import com.querydsl.core.annotations.QueryProjection;
import com.trip.hotel_gabriella.common.domain.Member;
import com.trip.hotel_gabriella.common.domain.Reservation;
import com.trip.hotel_gabriella.common.interfaces.model.GenericResponseEntityAdapter;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReservationInfo extends BaseDTO implements GenericResponseEntityAdapter<Reservation> {
    private Long id;
    private String checkIn;
    private String checkOut;
    private String name;
    private String phone;
    private int capacity;
    private boolean isMember;
    private boolean isCanceled;
    private Member member;

    @QueryProjection
    public ReservationInfo(Long id, String checkIn, String checkOut, String name, String phone, int capacity) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.name = name;
        this.phone = phone;
        this.capacity = capacity;
    }

    @Override
    public ReservationInfo fromEntity(Reservation reservation) {
        this.id = reservation.getId();
        this.checkIn = reservation.getCheckIn().toString();
        this.checkOut = reservation.getCheckOut().toString();
        this.name = reservation.getName();
        this.phone = reservation.getPhone();
        this.capacity = reservation.getCapacity();
        this.isMember = reservation.getIsMember();
        this.isCanceled = reservation.getIsCanceled();
        this.member = reservation.getMember();

        return this;
    }
}
