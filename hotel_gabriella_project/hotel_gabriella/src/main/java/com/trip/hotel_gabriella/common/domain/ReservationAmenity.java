package com.trip.hotel_gabriella.common.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "RESERVATION_AMENITY")
public class ReservationAmenity {
    @Id @GeneratedValue
    @Column(name = "RESERVATION_AMENITY_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESERVATION_ID")
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AMENITY_ID")
    private Amenity amenity;

    private int count;

}
