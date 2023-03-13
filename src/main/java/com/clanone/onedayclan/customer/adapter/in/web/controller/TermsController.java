package com.clanone.onedayclan.customer.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.TermsResponse;
import com.clanone.onedayclan.customer.application.port.in.TermsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TermsController {

    private final TermsPort termPort;

    @GetMapping("/terms")
    public ResponseEntity<OnedayclanResponse<List<TermsResponse>>> getTerms(){
        return ResponseEntity.ok(OnedayclanResponse.of(termPort.getTerms()));
    }
}
