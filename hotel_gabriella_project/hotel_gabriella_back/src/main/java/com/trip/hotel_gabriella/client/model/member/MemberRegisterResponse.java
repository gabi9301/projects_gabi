package com.trip.hotel_gabriella.client.model.member;

import com.trip.hotel_gabriella.client.model.BaseDTO;
import com.trip.hotel_gabriella.common.domain.Member;
import lombok.Getter;

@Getter
public class MemberRegisterResponse extends BaseDTO implements GenericResponseEntityAdatper<Member> {

    private Long id;

    @Override
    public MemberRegisterResponse fromEntity(Member member) {
        this.id = member.getId();
        return this;
    }


}
