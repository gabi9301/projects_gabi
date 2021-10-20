package com.trip.hotel_gabriella.client.model;

import com.trip.hotel_gabriella.common.domain.BaseEntity;
import com.trip.hotel_gabriella.common.domain.Member;
import lombok.Getter;

@Getter
public class MemberJoinResponse extends BaseDto implements GenericResponse{

    private Long id;

    @Override
    public BaseDto toDto(BaseEntity entity) {
        Member member = (Member) entity;

        this.id = member.getId();
        return this;
    }
}
