package com.trip.hotel_gabriella.common.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter @Setter
@DynamicInsert
@Entity
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID")
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

    public Room() {
    }

    public Room(int no, int floor, String roomType, String viewType) {
        this.no = no;
        this.floor = floor;
        this.roomType = roomType;
        this.viewType = viewType;
    }
}
