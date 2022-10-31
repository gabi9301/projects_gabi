package com.trip.hotel_gabriella.user.model.reservation;

import com.trip.hotel_gabriella.common.validation.annotation.Phone;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
public class ReservationReadRequest {

    @NotBlank(message = "이름은 필수항목입니다.")
    private String name;

    @Phone
    @NotBlank(message = "전화번호는 필수항목입니다.")
    private String phone;
}
