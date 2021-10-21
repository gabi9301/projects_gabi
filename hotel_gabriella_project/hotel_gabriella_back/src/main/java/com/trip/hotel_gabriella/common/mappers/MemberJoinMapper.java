package com.trip.hotel_gabriella.common.mappers;

import com.trip.hotel_gabriella.client.model.member.MemberRegisterRequest;
import com.trip.hotel_gabriella.common.domain.Member;

//@Mapper(componentModel = "spring")
public interface MemberJoinMapper extends GenericMapper<MemberRegisterRequest, Member>{
   // MemberJoinMapper instance = Mappers.getMapper(MemberJoinMapper.class);

}
