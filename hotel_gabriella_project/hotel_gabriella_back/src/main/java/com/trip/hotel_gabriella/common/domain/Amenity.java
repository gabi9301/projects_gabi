package com.trip.hotel_gabriella.common.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Amenity extends BaseEntity {
    @Id @Generated
    @Column(name = "amenity_id")
    private Long id;
    @NotBlank(message = "제목은 필수 항목입니다.")
    private String title;

    private String description;

    @NotNull(message = "가격은 필수 항목입니다.")
    @PositiveOrZero
    private int price;

}
