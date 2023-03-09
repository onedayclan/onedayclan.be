package com.clanone.onedayclan.customer.application.port.in;

import com.clanone.onedayclan.customer.adapter.in.web.request.PostInquiryRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryListResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryResponse;

import java.util.List;

public interface InquiryPort {
    void postInquiry(PostInquiryRequest inquiryRequest, String userId);
    InquiryResponse inquiryAnswer(Long seq, String userId);
    List<InquiryListResponse> inquiryList(String userId);
}
