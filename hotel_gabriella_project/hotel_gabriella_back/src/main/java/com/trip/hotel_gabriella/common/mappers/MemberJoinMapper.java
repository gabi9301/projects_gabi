package com.trip.hotel_gabriella.common.mappers;

import com.trip.hotel_gabriella.client.model.MemberJoinRequest;
import com.trip.hotel_gabriella.common.domain.Member;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

//@Mapper(componentModel = "spring")
public interface MemberJoinMapper extends GenericMapper<MemberJoinRequest, Member>{
   // MemberJoinMapper instance = Mappers.getMapper(MemberJoinMapper.class);

}
