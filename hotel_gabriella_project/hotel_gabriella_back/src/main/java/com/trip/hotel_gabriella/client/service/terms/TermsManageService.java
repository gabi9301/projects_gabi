package com.trip.hotel_gabriella.client.service.terms;

import com.trip.hotel_gabriella.client.model.terms.TermsRegisterRequest;
import com.trip.hotel_gabriella.client.model.terms.TermsRegisterResponse;
import com.trip.hotel_gabriella.common.domain.Member;
import com.trip.hotel_gabriella.common.domain.TermsHistory;

import java.util.List;

public interface TermsManageService {

    public void processTerms(List<TermsRegisterRequest> termsRegisterRequests, Member member);

    //public List<TermsHistory> convertTermsForHistory(TermsRegisterRequest termsRegisterRequest);

    public TermsRegisterResponse registerTerms(TermsHistory termsHistory);

    public void registerTermsList(List<TermsHistory> termsHistories);


}
