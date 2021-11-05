package com.trip.hotel_gabriella.user.model.member;

import com.trip.hotel_gabriella.common.interfaces.model.GenericResponseEntityAdapter;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import com.trip.hotel_gabriella.common.domain.Member;
import lombok.Getter;

@Getter
public class MemberAuthInfo extends BaseDTO implements GenericResponseEntityAdapter<Member> {

    private Long id;
    private String account;


    @Override
    public MemberAuthInfo fromEntity(Member member) {
        this.id = member.getId();
        this.account = member.getAccount();
        return this;
    }
}
