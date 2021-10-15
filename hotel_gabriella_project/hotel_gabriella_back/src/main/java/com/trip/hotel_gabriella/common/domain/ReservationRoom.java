package com.trip.hotel_gabriella.common.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "reservation_room")
public class ReservationRoom {

    @Id @GeneratedValue
    @Column(name = "reservation_room_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public void changeReservation(Reservation reservation) {
        if(this.reservation != null) {
            this.reservation.getReservationRooms().remove(this);
        }
        this.reservation = reservation;
        reservation.getReservationRooms().add(this);
    }

}
