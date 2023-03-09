package com.clanone.onedayclan.customer.application.port.out;

import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryAnswerResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryDto;

import java.util.List;

public interface GetInquiryPort {
    InquiryDto getInquiry(String userId, Long seq);
    List<InquiryAnswerResponse> getInquiryAnswer(Long seq);
}
