package com.trip.hotel_gabriella.common.domain;

import com.trip.hotel_gabriella.common.validation.annotation.Zipcode;
import lombok.*;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address extends BaseEntity{
    @NotBlank(message = "도시명은 필수항목입니다.")
    private String city;

    @NotBlank(message = "거리명은 필수항목입니다.")
    private String street;

    private String address_detail;

    @NotBlank(message = "우편번호는 필수항목입니다.")
    @Zipcode
    private String zipcode;

}
