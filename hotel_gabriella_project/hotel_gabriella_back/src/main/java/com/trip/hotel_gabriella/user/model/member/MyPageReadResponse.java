package com.trip.hotel_gabriella.user.model.member;

import com.trip.hotel_gabriella.common.interfaces.model.GenericHttpResponseTransferor;
import com.trip.hotel_gabriella.common.model.MemberInfo;
import com.trip.hotel_gabriella.common.model.ReservationInfo;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MyPageReadResponse extends BaseDTO implements GenericHttpResponseTransferor {

    private MemberInfo memberInfo;
    private List<ReservationInfo> reservationInfoList;

}
