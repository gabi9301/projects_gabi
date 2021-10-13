package com.trip.hotel_gabriella.common.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Address {
    private String city;
    private String street;
    private String address_detail;
    private String zipcode;

    public Address() {
    }

    @SuppressWarnings("unused")
    public Address(String city, String street, String address_detail, String zipcode) {
        this.city = city;
        this.street = street;
        this.address_detail = address_detail;
        this.zipcode = zipcode;
    }
}
