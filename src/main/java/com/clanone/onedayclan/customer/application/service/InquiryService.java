package com.clanone.onedayclan.customer.application.service;

import com.clanone.onedayclan.customer.adapter.in.web.request.InquiryAnswerCreateRequest;
import com.clanone.onedayclan.customer.adapter.in.web.request.InquirySearchRequest;
import com.clanone.onedayclan.customer.adapter.in.web.request.PostInquiryRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.*;
import com.clanone.onedayclan.customer.adapter.out.model.InquirySearchModel;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.InquiryEntity;
import com.clanone.onedayclan.customer.application.exception.InvalidPostingMemberException;
import com.clanone.onedayclan.customer.application.port.in.InquiryPort;
import com.clanone.onedayclan.customer.application.port.out.GetInquiryPort;
import com.clanone.onedayclan.customer.application.port.out.ManageInquiryPort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class InquiryService implements InquiryPort {

    private final ManageInquiryPort manageInquiryPort;
    private final GetInquiryPort getInquiryPort;

    @Override
    public void postInquiry(PostInquiryRequest inquiryRequest, String userId) {
        manageInquiryPort.saveInquiry(inquiryRequest, userId);
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

    @Override
    public Page<AdminInquiryResponse> getInquiryListForAdmin(InquirySearchRequest request, Pageable pageable) {
        return getInquiryPort.getInquiryListForAdmin(InquirySearchModel.builder()
                        .userId(request.getUserId())
                        .name(request.getName())
                        .searchStartAt(request.getSearchStartAt())
                        .searchEndAt(request.getSearchEndAt())
                        .answerYn(request.getAnswerYn())
                        .deleteYn(request.getDeleteYn())
                        .build(), pageable);
    }

    @Override
    public AdminInquiryDetailResponse getInquiryForAdmin(long inquirySeq) {
        return getInquiryPort.getInquiryForAdmin(inquirySeq);
    }

    @Override
    @Transactional
    public void insertInquiryAnswer(long inquirySeq, InquiryAnswerCreateRequest request) {
        InquiryEntity inquiry = getInquiryPort.getInquiry(inquirySeq);
        manageInquiryPort.saveInquiryAnswer(request, inquiry);
        inquiry.answered();
    }

    @Override
    @Transactional
    public void deleteInquiryAnswer(long inquirySeq) {
        InquiryEntity inquiry = getInquiryPort.getInquiry(inquirySeq);
        manageInquiryPort.deleteInquiryAnswer(inquirySeq);
        inquiry.notAnswered();
    }


}
