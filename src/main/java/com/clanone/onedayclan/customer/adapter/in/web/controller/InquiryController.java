package com.clanone.onedayclan.customer.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.common.resolver.LoginUserId;
import com.clanone.onedayclan.customer.adapter.in.web.request.PostInquiryRequest;
import com.clanone.onedayclan.customer.application.port.in.InquiryPort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class InquiryController {

    private final InquiryPort inquiryPort;

    @PostMapping("/inquiry")
    public OnedayclanResponse<Void> postInquiry(@LoginUserId String userId, @Valid @RequestBody PostInquiryRequest inquiryRequest) {
        inquiryPort.postInquiry(inquiryRequest, userId);
        return OnedayclanResponse.success();
    }
}
