package com.trip.hotel_gabriella.common.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "reservation_amenity")
public class ReservationAmenity {
    @Id @GeneratedValue
    @Column(name = "reservation_amenity_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "amenity_id")
    private Amenity amenity;

    private int count;

    public void changeReservation(Reservation reservation) {
        if(this.reservation != null) {
            this.reservation.getReservationAmenities().remove(this);
        }
        this.reservation = reservation;
        reservation.getReservationAmenities().add(this);
    }




}
