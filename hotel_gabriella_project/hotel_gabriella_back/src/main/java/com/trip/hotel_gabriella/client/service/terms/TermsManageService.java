package com.trip.hotel_gabriella.client.service.terms;

import com.trip.hotel_gabriella.client.model.terms.TermsRegisterRequest;
import com.trip.hotel_gabriella.client.model.terms.TermsRegisterResponse;
import com.trip.hotel_gabriella.common.domain.Member;
import com.trip.hotel_gabriella.common.domain.TermsHistory;

import java.util.List;

public interface TermsManageService {

    void processTerms(List<TermsRegisterRequest> termsRegisterRequests, Member member);

    //List<TermsHistory> convertTermsForHistory(TermsRegisterRequest termsRegisterRequest);

    TermsRegisterResponse registerTerms(TermsHistory termsHistory);

    void registerTermsList(List<TermsHistory> termsHistories);


}
