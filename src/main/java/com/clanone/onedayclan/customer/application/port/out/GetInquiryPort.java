package com.clanone.onedayclan.customer.application.port.out;

import com.clanone.onedayclan.customer.adapter.in.web.response.*;
import com.clanone.onedayclan.customer.adapter.out.model.InquirySearchModel;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.InquiryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GetInquiryPort {
    InquiryDto getInquiryDto(String userId, long seq);
    List<InquiryAnswerResponse> getInquiryAnswer(long seq);
    List<InquiryListResponse> getInquiryList(String userId);
    InquiryEntity getInquiry(long seq);
    Page<AdminInquiryResponse> getInquiryListForAdmin(InquirySearchModel model, Pageable pageable);
    AdminInquiryDetailResponse getInquiryForAdmin(long inquirySeq);
}
