package com.trip.hotel_gabriella.user.model.terms;


import lombok.Getter;

import com.trip.hotel_gabriella.common.interfaces.model.GenericResponseEntityAdapter;
import com.trip.hotel_gabriella.user.model.BaseDTO;
import com.trip.hotel_gabriella.common.domain.TermsHistory;

@Getter
public class TermsRegisterResponse extends BaseDTO implements GenericResponseEntityAdapter<TermsHistory> {
    private Long registeredTermsId;

    @Override
    public TermsRegisterResponse fromEntity(TermsHistory termsHistory) {
        this.registeredTermsId = termsHistory.getId();
        return this;
    }
}
