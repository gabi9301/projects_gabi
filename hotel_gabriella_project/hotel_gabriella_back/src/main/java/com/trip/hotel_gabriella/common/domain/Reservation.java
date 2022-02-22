package com.trip.hotel_gabriella.common.domain;

import com.trip.hotel_gabriella.common.validation.annotation.Phone;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;

    @NotNull(message = "체크인 시간은 필수항목 입니다.")
    private LocalDateTime checkIn;

    @NotNull(message = "체크아웃 시간은 필수항목 입니다.")
    private LocalDateTime checkOut;

    @NotBlank(message = "이름은 필수항목 입니다.")
    private String name;

    @NotBlank(message = "휴대폰 번호는 필수항목 입니다.")
    @Phone
    private String phone;

    @NotNull(message = "정원은 필수항목 입니다.")
    @Min(1)
    @Max(10)
    private int capacity;

    @NotNull(message = "회원여부는 필수항목입니다.")
    private Boolean isMember;

    private Boolean isCanceled = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "reservation")
    @Builder.Default
    private List<ReservationRoom> reservationRooms = new ArrayList<>();

    @OneToMany(mappedBy = "reservation")
    @Builder.Default
    private List<ReservationAmenity> reservationAmenities = new ArrayList<>();

    public void changeMember(Member member) {
        if (this.member != null) {  //이미 예약자가 설정된 상태에서 바꿀 경우
            this.member.getReservations().remove(this);
        }
        this.member = member;
        member.getReservations().add(this);
    }

    public void changeCheckIn(LocalDateTime localDateTime) {
        this.checkIn = localDateTime;
    }

    public void changeCheckOut(LocalDateTime localDateTime) {
        this.checkOut = localDateTime;
    }

    public void markAsCancel() {
        this.isCanceled = true;
    }


}
