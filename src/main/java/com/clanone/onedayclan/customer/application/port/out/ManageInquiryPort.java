package com.clanone.onedayclan.customer.application.port.out;

import com.clanone.onedayclan.customer.adapter.in.web.request.InquiryAnswerCreateRequest;
import com.clanone.onedayclan.customer.adapter.in.web.request.PostInquiryRequest;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.InquiryEntity;

public interface ManageInquiryPort {
    void saveInquiry(PostInquiryRequest inquiryRequest, String userId);
    void saveInquiryAnswer(InquiryAnswerCreateRequest request, InquiryEntity inquiry);
    void deleteInquiryAnswer(long inquirySeq);
}
