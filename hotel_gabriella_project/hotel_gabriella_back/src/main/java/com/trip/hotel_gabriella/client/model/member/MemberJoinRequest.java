package com.trip.hotel_gabriella.client.model.member;

import lombok.Getter;

import com.trip.hotel_gabriella.client.model.BaseDTO;
import com.trip.hotel_gabriella.client.model.common.GenericHttpRequestTransferor;
import com.trip.hotel_gabriella.client.model.terms.TermsRegisterRequest;

import javax.validation.Valid;
import java.util.List;

@Getter
public class MemberJoinRequest extends BaseDTO implements GenericHttpRequestTransferor {
    @Valid
    private MemberRegisterRequest memberRegisterRequest;

    private List<TermsRegisterRequest> terms;

}
