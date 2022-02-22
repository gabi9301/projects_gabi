package com.trip.hotel_gabriella.user.model.reservation;

import com.trip.hotel_gabriella.common.domain.ViewType;
import com.trip.hotel_gabriella.common.interfaces.model.GenericHttpRequestTransferor;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class BookingCommand extends BaseDTO implements GenericHttpRequestTransferor {

    @Valid
    private ReserveRequest reserveRequest;

    @NotNull(message = "방 아이디는 필수 항목입니다.")
    private Long roomId;

    @NotNull(message = "전망은 필수항목입니다.")
    private ViewType viewType;


}
