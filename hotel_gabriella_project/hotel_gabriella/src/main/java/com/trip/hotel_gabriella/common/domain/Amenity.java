package com.trip.hotel_gabriella.common.domain;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter
@Entity
public class Amenity {
    @Id @Generated
    @Column(name = "AMENITY_ID")
    private Long id;
    private String title;
    private String description;
    private int price;

}
