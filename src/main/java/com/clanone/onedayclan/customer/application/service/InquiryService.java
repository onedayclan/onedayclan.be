package com.clanone.onedayclan.customer.application.service;

import com.clanone.onedayclan.customer.adapter.in.web.request.PostInquiryRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryDto;
import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryListResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryResponse;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.InquiryEntity;
import com.clanone.onedayclan.customer.application.exception.InvalidPostingMemberException;
import com.clanone.onedayclan.customer.application.port.in.InquiryPort;
import com.clanone.onedayclan.customer.application.port.out.GetInquiryPort;
import com.clanone.onedayclan.customer.application.port.out.SaveInquiryPort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public InquiryResponse inquiryAnswer(long seq, String userId) {
        InquiryDto inquiryResult = getInquiryPort.getInquiryDto(userId, seq);
        InquiryResponse inquiryResponse = InquiryResponse.builder()
                .title(inquiryResult.getTitle())
                .content(inquiryResult.getContent())
                .createdAt(inquiryResult.getCreatedAt())
                .answers(new ArrayList<>())
                .build();

        if (inquiryResult.isAnswerYn()) {
            inquiryResponse.addAnswerList(getInquiryPort.getInquiryAnswer(seq));
        }
        return inquiryResponse;
    }

    @Override
    public List<InquiryListResponse> inquiryList(String userId) {
        return getInquiryPort.getInquiryList(userId);
    }

    @Override
    @Transactional
    public void deleteInquiry(String userId, long seq) {
        InquiryEntity inquiry = getInquiryPort.getInquiry(seq);

        if (!inquiry.getMember().getUserId().equals(userId)) {
            throw new InvalidPostingMemberException();
        }
        inquiry.delete();
    }
}
