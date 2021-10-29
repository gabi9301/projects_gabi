package com.trip.hotel_gabriella.client.model.member;

import lombok.Getter;

import com.trip.hotel_gabriella.client.model.BaseDTO;
import com.trip.hotel_gabriella.client.model.common.GenericResponseEntityAdapter;
import com.trip.hotel_gabriella.common.domain.Member;

@Getter
public class MemberRegisterResponse extends BaseDTO implements GenericResponseEntityAdapter<Member> {

    private Long id;

    @Override
    public MemberRegisterResponse fromEntity(Member member) {
        this.id = member.getId();
        return this;
    }


}
