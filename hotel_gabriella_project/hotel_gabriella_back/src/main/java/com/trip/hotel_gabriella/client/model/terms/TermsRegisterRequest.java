package com.trip.hotel_gabriella.client.model.terms;

import com.trip.hotel_gabriella.common.validation.annotation.Agreement;
import lombok.Getter;

import com.trip.hotel_gabriella.client.model.BaseDTO;
import com.trip.hotel_gabriella.client.model.common.GenericRequestEntityAdapter;
import com.trip.hotel_gabriella.common.domain.Member;
import com.trip.hotel_gabriella.common.domain.TermsHistory;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
public class TermsRegisterRequest extends BaseDTO implements GenericRequestEntityAdapter<TermsHistory> {

    @NotBlank(message = "동의항목 코드는 필수항목 입니다.")
    private String termsCode;

    @NotBlank(message = "동의여부는 필수항목 입니다.")
    @Agreement
    private String agreeYn;

    private Member member;

    List<TermsRegisterRequest> terms;

    public void setTargetMember(Member member) {
        if(this.member == null) {
            this.member = member;
        }
    }


    @Override
    public TermsHistory toEntity() {
        return TermsHistory.builder()
                            .termsCode(termsCode)
                            .agreeYn(agreeYn)
                            .member(member)
                            .build();
    }
}
