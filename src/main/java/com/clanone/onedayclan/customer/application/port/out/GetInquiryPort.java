package com.clanone.onedayclan.customer.application.port.out;

import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryAnswerResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryDto;
import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryListResponse;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.InquiryEntity;

import java.util.List;

public interface GetInquiryPort {
    InquiryDto getInquiry(String userId, Long seq);
    List<InquiryAnswerResponse> getInquiryAnswer(Long seq);
    List<InquiryListResponse> getInquiryList(String userId);
}
