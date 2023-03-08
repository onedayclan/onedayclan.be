package com.clanone.onedayclan.customer.application.service;

import com.clanone.onedayclan.customer.adapter.in.web.request.PostInquiryRequest;
import com.clanone.onedayclan.customer.application.port.in.InquiryPort;
import com.clanone.onedayclan.customer.application.port.out.SaveInquiryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InquiryService implements InquiryPort {

    private final SaveInquiryPort saveInquiryPort;

    @Override
    public void postInquiry(PostInquiryRequest inquiryRequest, String userId) {
        saveInquiryPort.saveInquiry(inquiryRequest, userId);
    }
}
