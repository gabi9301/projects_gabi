package com.trip.hotel_gabriella.client.controller;

import com.trip.hotel_gabriella.client.service.terms.TermsManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestTermsController {

    private final TermsManageService termsMangeService;


}
