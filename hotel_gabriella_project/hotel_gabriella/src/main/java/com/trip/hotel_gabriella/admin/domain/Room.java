package com.trip.hotel_gabriella.admin.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Room {
    @Id @GeneratedValue
    private Long id;

    private int no;
    private int floor;
    private String roomType;
    private String viewType;

    public Room() {
    }

    public Room(int no, int floor, String roomType, String viewType) {
        this.no = no;
        this.floor = floor;
        this.roomType = roomType;
        this.viewType = viewType;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }
}
