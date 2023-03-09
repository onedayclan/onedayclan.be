package com.clanone.onedayclan.customer.application.service;

import com.clanone.onedayclan.customer.adapter.in.web.request.PostInquiryRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryDto;
import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryListResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryResponse;
import com.clanone.onedayclan.customer.application.port.in.InquiryPort;
import com.clanone.onedayclan.customer.application.port.out.GetInquiryPort;
import com.clanone.onedayclan.customer.application.port.out.SaveInquiryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InquiryService implements InquiryPort {

    private final SaveInquiryPort saveInquiryPort;
    private final GetInquiryPort getInquiryPort;

    @Override
    public void postInquiry(PostInquiryRequest inquiryRequest, String userId) {
        saveInquiryPort.saveInquiry(inquiryRequest, userId);
    }

    @Override
    public InquiryResponse inquiryAnswer(Long seq, String userId) {
        InquiryDto inquiryResult = getInquiryPort.getInquiry(userId, seq);
        InquiryResponse inquiryResponse = InquiryResponse.builder()
                .title(inquiryResult.getTitle())
                .content(inquiryResult.getContent())
                .createdAt(inquiryResult.getCreatedAt())
                .build();

        if (inquiryResult.isAnswerYn()) {
            inquiryResponse.updateAnswerList(getInquiryPort.getInquiryAnswer(seq));
        }
        return inquiryResponse;
    }

    @Override
    public List<InquiryListResponse> inquiryList(String userId) {
        return getInquiryPort.getInquiryList(userId);
    }
}
