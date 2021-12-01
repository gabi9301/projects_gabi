package com.trip.hotel_gabriella.user.service.member;

import com.trip.hotel_gabriella.user.model.member.MemberAuthInfo;
import com.trip.hotel_gabriella.user.model.member.MemberLoginCommand;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberDetailsService extends UserDetailsService {

    //MemberAuthInfo getMemberAuthInfo(MemberLoginCommand memberLoginCommand);

}
