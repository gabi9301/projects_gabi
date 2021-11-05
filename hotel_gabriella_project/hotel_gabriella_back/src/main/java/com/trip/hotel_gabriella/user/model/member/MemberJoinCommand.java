package com.trip.hotel_gabriella.user.model.member;


import lombok.Getter;

import com.trip.hotel_gabriella.user.model.BaseDTO;
import com.trip.hotel_gabriella.common.interfaces.model.GenericHttpRequestTransferor;
import com.trip.hotel_gabriella.user.model.terms.TermsRegisterRequest;

import javax.validation.Valid;
import java.util.List;

@Getter
public class MemberJoinCommand extends BaseDTO implements GenericHttpRequestTransferor {
    @Valid
    private MemberRegisterRequest memberRegisterRequest;

    private List<TermsRegisterRequest> terms;

}
