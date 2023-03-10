package com.clanone.onedayclan.customer.application.port.out;

import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryAnswerResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryDto;
import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryListResponse;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.InquiryEntity;

import java.util.List;

public interface GetInquiryPort {
    InquiryDto getInquiryDto(String userId, long seq);
    List<InquiryAnswerResponse> getInquiryAnswer(long seq);
    List<InquiryListResponse> getInquiryList(String userId);
    InquiryEntity getInquiry(long seq);
}
