package com.trip.hotel_gabriella.client.model.member;

import com.trip.hotel_gabriella.client.model.BaseDTO;
import com.trip.hotel_gabriella.client.model.terms.TermsRegisterRequest;
import lombok.Getter;

@Getter
public class MemberJoinRequest extends BaseDTO {

    private MemberRegisterRequest memberRegisterRequest;

    private TermsRegisterRequest termsRegisterRequest;

}
