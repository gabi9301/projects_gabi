package com.trip.hotel_gabriella.client.service.terms;

import lombok.RequiredArgsConstructor;

import com.trip.hotel_gabriella.client.model.terms.TermsRegisterRequest;
import com.trip.hotel_gabriella.client.model.terms.TermsRegisterResponse;
import com.trip.hotel_gabriella.client.repository.TermsRepository;
import com.trip.hotel_gabriella.common.domain.Member;
import com.trip.hotel_gabriella.common.domain.TermsHistory;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BasicTermsManageService implements TermsManageService {

    private final TermsRepository termsRepository;

    @Transactional
    public void processTerms(List<TermsRegisterRequest> termsRegisterRequestList, Member member) {
        List<TermsHistory> termsHistories = new ArrayList<TermsHistory>();

        for (TermsRegisterRequest termsItem : termsRegisterRequestList) {
            if(termsItem.getMember()==null){
                termsItem.setTargetMember(member);
            }
            TermsHistory item = termsItem.toEntity();
            termsHistories.add(item);
        }
        registerTermsList(termsHistories);
    }

    @Transactional
    public TermsRegisterResponse registerTerms(TermsHistory termsHistory) {

        termsRepository.save(termsHistory);

        return new TermsRegisterResponse().fromEntity(termsHistory);
    }

    @Transactional
    public void registerTermsList(List<TermsHistory> termsHistories) {
        for (TermsHistory termsHistory : termsHistories) {
           registerTerms(termsHistory);
        }

    }


}
