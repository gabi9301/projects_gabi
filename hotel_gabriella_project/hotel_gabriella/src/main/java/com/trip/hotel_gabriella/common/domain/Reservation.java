package com.trip.hotel_gabriella.common.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Reservation {
    @Id @GeneratedValue
    @Column(name = "RESERVATION_ID")
    private Long id;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String name;
    private String phone;
    private int capacity;
    @OneToMany(mappedBy = "reservation")
    private List<ReservationRoom> reservationRooms = new ArrayList<>();
    @OneToMany(mappedBy = "amenity")
    private List<ReservationAmenity> reservationAmenities = new ArrayList<>();

    private Boolean isMember;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
