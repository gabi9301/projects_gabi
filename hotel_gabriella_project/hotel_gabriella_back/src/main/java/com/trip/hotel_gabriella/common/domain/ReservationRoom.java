package com.trip.hotel_gabriella.common.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reservation_room")
public class ReservationRoom {

    @Id @GeneratedValue
    @Column(name = "reservation_room_id")
    private Long id;

    @NotNull(message = "예약자 정보는 필수입니다.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @NotNull(message = "방 정보는 필수입니다.")
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
