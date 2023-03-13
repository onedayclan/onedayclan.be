package com.clanone.onedayclan.customer.application.port.in;

import com.clanone.onedayclan.customer.adapter.in.web.request.InquiryAnswerCreateRequest;
import com.clanone.onedayclan.customer.adapter.in.web.request.InquirySearchRequest;
import com.clanone.onedayclan.customer.adapter.in.web.request.PostInquiryRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminInquiryDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminInquiryResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryListResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InquiryPort {
    void postInquiry(PostInquiryRequest inquiryRequest, String userId);
    InquiryResponse inquiryAnswer(long seq, String userId);
    List<InquiryListResponse> inquiryList(String userId);
    void deleteInquiry(String userId, long seq);
    Page<AdminInquiryResponse> getInquiryListForAdmin(InquirySearchRequest request, Pageable pageable);
    AdminInquiryDetailResponse getInquiryForAdmin(long inquirySeq);
    void insertInquiryAnswer(long inquirySeq, InquiryAnswerCreateRequest request);
    void deleteInquiryAnswer(long inquirySeq);
}
