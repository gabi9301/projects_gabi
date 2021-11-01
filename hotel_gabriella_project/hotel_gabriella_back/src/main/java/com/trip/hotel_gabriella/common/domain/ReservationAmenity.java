package com.trip.hotel_gabriella.common.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reservation_amenity")
public class ReservationAmenity {
    @Id @GeneratedValue
    @Column(name = "reservation_amenity_id")
    private Long id;

    @NotNull(message = "예약자 정보는 필수입니다.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @NotNull(message = "어매니티 정보는 필수입니다.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "amenity_id")
    private Amenity amenity;

    @NotNull(message = "이용자 수는 필수항목 입니다.")
    @Positive
    private int count;

    public void changeReservation(Reservation reservation) {
        if(this.reservation != null) {
            this.reservation.getReservationAmenities().remove(this);
        }
        this.reservation = reservation;
        reservation.getReservationAmenities().add(this);
    }




}
