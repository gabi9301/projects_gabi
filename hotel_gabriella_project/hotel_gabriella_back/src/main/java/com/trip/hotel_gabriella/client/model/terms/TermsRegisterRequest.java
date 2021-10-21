package com.trip.hotel_gabriella.client.model.terms;

import com.trip.hotel_gabriella.client.model.BaseDTO;
import com.trip.hotel_gabriella.client.model.member.GenericRequestEntityAdapter;
import com.trip.hotel_gabriella.common.domain.BaseEntity;
import com.trip.hotel_gabriella.common.domain.Member;
import com.trip.hotel_gabriella.common.domain.TermsHistory;
import lombok.Getter;

import java.util.List;

@Getter
public class TermsRegisterRequest extends BaseDTO implements GenericRequestEntityAdapter<TermsHistory> {
    private String termCode;
    private String agreeYn;
    private Member member;
    private List<TermsRegisterRequest> termsRegisterRequestList;

    @Override
    public TermsHistory toEntity() {
        return TermsHistory.builder()
                .termCode(termCode)
                .agreeYn(agreeYn)
                .member(member)
                .build();
    }
}
