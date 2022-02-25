package com.trip.hotel_gabriella.user.model.reservation;

import com.trip.hotel_gabriella.common.domain.Reservation;
import com.trip.hotel_gabriella.common.domain.ViewType;
import com.trip.hotel_gabriella.common.interfaces.model.GenericRequestEntityAdapter;
import com.trip.hotel_gabriella.common.validation.annotation.Phone;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class ReserveRequest extends BaseDTO implements GenericRequestEntityAdapter<Reservation> {

    @NotBlank(message = "체크인 시간은 필수항목 입니다.")
    private String checkIn;

    @NotBlank(message = "체크아웃 시간은 필수항목 입니다.")
    private String checkOut;

    @NotBlank(message = "이름은 필수항목 입니다.")
    private String name;

    @NotBlank(message = "휴대폰 번호는 필수항목 입니다.")
    @Phone
    private String phone;

    @NotNull(message = "정원은 필수항목 입니다.")
    @Min(1) @Max(10)
    private int capacity;


    @NotNull(message = "회원여부는 필수항목입니다.")
    private Boolean isMember;



    @Override
    public Reservation toEntity() {

        return Reservation.builder()
                .name(name)
                .phone(phone)
                .capacity(capacity)
                .isMember(isMember)
                .build();

    }
}
