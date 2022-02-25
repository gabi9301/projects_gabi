package com.trip.hotel_gabriella.user.service.member;

import com.trip.hotel_gabriella.common.model.MemberInfo;
import com.trip.hotel_gabriella.common.model.ReservationInfo;
import com.trip.hotel_gabriella.user.model.member.MyPageModifyCommand;
import com.trip.hotel_gabriella.user.model.member.MyPageReadResponse;

import java.util.List;

public interface MyPageService {

    MyPageReadResponse readMyPage(Long id);

    MemberInfo modifyMyPage(MyPageModifyCommand myPageModifyCommand);

    MemberInfo readMyPagePersonalInfo(Long id);

    List<ReservationInfo> readMyPageReservationInfo(Long id);

}
