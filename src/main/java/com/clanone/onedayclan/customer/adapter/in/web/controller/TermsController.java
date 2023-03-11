package com.clanone.onedayclan.customer.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.TermResponse;
import com.clanone.onedayclan.customer.application.port.in.TermPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TermsController {

    private final TermPort termPort;

    @GetMapping("/terms")
    public ResponseEntity<OnedayclanResponse<List<TermResponse>>> getTerm(){
        return ResponseEntity.ok(OnedayclanResponse.of(termPort.getTerm()));
    }
}
