package com.trip.hotel_gabriella.client.service.terms;

import com.trip.hotel_gabriella.client.model.terms.TermsRegisterRequest;
import com.trip.hotel_gabriella.client.model.terms.TermsRegisterResponse;
import com.trip.hotel_gabriella.common.domain.TermsHistory;

import java.util.ArrayList;
import java.util.List;

public class BasicTermsManageService implements TermsManageService {
    @Override
    public void saveTerms(TermsRegisterRequest termsRegisterRequest) {
        //컨버팅하고 레지스터
    }

    @Override
    public List<TermsHistory> convertTermsForHistory(TermsRegisterRequest termsRegisterRequest) {
        List<TermsHistory> termsHistories = new ArrayList<TermsHistory>();
        List<TermsRegisterRequest> termsRegisterRequests = termsRegisterRequest.getTerms();
        
        for (TermsRegisterRequest termRegisterRequest : termsRegisterRequests) {
            System.out.println("termRegisterRequest.getTermsCode() = " + termRegisterRequest.getTermsCode());
            TermsHistory item = TermsHistory.builder()
                    .termsCode(termsRegisterRequest.getTermsCode())
                    .agreeYn(termRegisterRequest.getAgreeYn())
                    .build();
        }
        return termsHistories;
    }

    @Override
    public TermsRegisterResponse registerTerms(List<TermsHistory> termsHistories) {
        //리포지토리를 사용해서 하나씩 DB에 넣기
        return null;
    }
}
