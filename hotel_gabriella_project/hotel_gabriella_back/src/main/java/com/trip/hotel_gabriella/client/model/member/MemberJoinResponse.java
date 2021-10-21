package com.trip.hotel_gabriella.client.model.member;

import com.trip.hotel_gabriella.client.model.BaseDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberJoinResponse extends BaseDTO {
    private String joinedMemberId;

}
