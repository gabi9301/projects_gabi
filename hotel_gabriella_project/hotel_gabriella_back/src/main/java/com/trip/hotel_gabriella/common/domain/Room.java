package com.trip.hotel_gabriella.common.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor //@Builder는 all-args 생성자 필수
@NoArgsConstructor  //@Entity 는 public or protected 인 no-args 생성자 필수
@DynamicInsert
@Entity
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    private int no;
    private int floor;
    private String roomType;
    private String viewType;
    private int price;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'AVAILABLE'")
    private Availability availability;
    private int capacity;


}
