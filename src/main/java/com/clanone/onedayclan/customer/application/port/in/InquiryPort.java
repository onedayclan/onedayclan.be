package com.clanone.onedayclan.customer.application.port.in;

import com.clanone.onedayclan.customer.adapter.in.web.request.PostInquiryRequest;

public interface InquiryPort {
    void postInquiry(PostInquiryRequest inquiryRequest, String userId);
}
