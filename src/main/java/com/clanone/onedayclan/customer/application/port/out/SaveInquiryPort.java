package com.clanone.onedayclan.customer.application.port.out;

import com.clanone.onedayclan.customer.adapter.in.web.request.PostInquiryRequest;

public interface SaveInquiryPort {
    void saveInquiry(PostInquiryRequest inquiryRequest, String userId);
}
