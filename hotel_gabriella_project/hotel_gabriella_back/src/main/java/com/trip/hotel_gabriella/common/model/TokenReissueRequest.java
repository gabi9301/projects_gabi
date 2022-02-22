package com.trip.hotel_gabriella.common.model;

import com.trip.hotel_gabriella.user.model.BaseDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class TokenReissueRequest extends BaseDTO {

    @Valid
    private TokenPayload tokenPayload;
    @NotBlank
    private String detailsServiceIdentifier;

    @Builder
    public TokenReissueRequest(TokenPayload tokenPayload, String detailsServiceIdentifier) {
        this.tokenPayload = tokenPayload;
        this.detailsServiceIdentifier = detailsServiceIdentifier;
    }

    public void changeServiceIdentifier(String detailsServiceIdentifier){
        this.detailsServiceIdentifier = detailsServiceIdentifier;
    }
}
