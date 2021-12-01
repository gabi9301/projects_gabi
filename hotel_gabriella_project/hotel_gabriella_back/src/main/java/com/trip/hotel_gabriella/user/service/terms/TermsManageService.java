package com.trip.hotel_gabriella.user.service.terms;

import com.trip.hotel_gabriella.user.model.terms.TermsRegisterRequest;
import com.trip.hotel_gabriella.common.domain.Member;
import com.trip.hotel_gabriella.common.domain.TermsHistory;

import java.util.List;

public interface TermsManageService {

    void processTerms(List<TermsRegisterRequest> termsRegisterRequests, Member member);

    //List<TermsHistory> convertTermsForHistory(TermsRegisterRequest termsRegisterRequest);

    void registerTerms(TermsHistory termsHistory);

    void registerTermsList(List<TermsHistory> termsHistories);


}
