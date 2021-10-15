package com.trip.hotel_gabriella.common.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Reservation {
    @Id @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private String name;
    private String phone;
    private int capacity;
    @OneToMany(mappedBy = "reservation")
    private List<ReservationRoom> reservationRooms = new ArrayList<>();
    @OneToMany(mappedBy = "reservation")
    private List<ReservationAmenity> reservationAmenities = new ArrayList<>();

    private Boolean isMember;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void changeMember(Member member) {
        if(this.member != null) {  //이미 예약자가 설정된 상태에서 바꿀 경우
            this.member.getReservations().remove(this);
        }
        this.member = member;
        member.getReservations().add(this);
    }

}
