package com.trip.hotel_gabriella.common.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;


@Getter
@Builder
@AllArgsConstructor //@Builder는 all-args 생성자 필수
@NoArgsConstructor(access = AccessLevel.PROTECTED) //@Entity 는 public or protected 인 no-args 생성자 필수
@Entity
public class Room extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @NotNull(message = "방번호는 필수항목 입니다.")
    @Column(unique = true)
    @PositiveOrZero
    private int no;

    @Positive
    private int floor;

    private String roomType;
    private String viewType;

    @PositiveOrZero
    private int price;

    @PositiveOrZero
    private int capacity;

    @Enumerated(EnumType.STRING)
    private Availability availability = Availability.AVAILABLE;



}
