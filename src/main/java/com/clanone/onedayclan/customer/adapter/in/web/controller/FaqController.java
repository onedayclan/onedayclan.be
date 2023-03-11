package com.clanone.onedayclan.customer.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.FaqResponse;
import com.clanone.onedayclan.customer.application.port.in.FaqPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FaqController {

    private final FaqPort faqPort;

    @GetMapping("/faq")
    public ResponseEntity<OnedayclanResponse<List<FaqResponse>>> getFaqList() {
        return ResponseEntity.ok(OnedayclanResponse.of(faqPort.getFaqList()));
    }
}

