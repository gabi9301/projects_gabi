package com.trip.hotel_gabriella.common.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Embeddable
@NotNull(message = "주소는 필수항목입니다.")
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
