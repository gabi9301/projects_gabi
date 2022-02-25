package com.trip.hotel_gabriella.user.model.search;

import com.trip.hotel_gabriella.common.interfaces.model.GenericHttpResponseTransferor;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class RoomSearchCommand extends BaseDTO implements GenericHttpResponseTransferor {

    @NotBlank(message = "체크인 시간은 필수항목 입니다.")
    private String checkIn;

    @NotBlank(message = "체크아웃 시간은 필수항목 입니다.")
    private String checkOut;

    @NotNull(message = "정원은 필수항목 입니다.")
    @Min(1) @Max(10)
    private int capacity;

    @NotBlank(message = "전망은 필수항목 입니다.")
    private String viewType;
}
