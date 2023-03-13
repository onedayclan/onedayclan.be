package com.clanone.onedayclan.customer.adapter.out.persistence;

import com.clanone.onedayclan.customer.adapter.in.web.request.InquiryAnswerCreateRequest;
import com.clanone.onedayclan.customer.adapter.in.web.request.PostInquiryRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.*;
import com.clanone.onedayclan.customer.adapter.out.model.InquirySearchModel;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.InquiryAnswerEntity;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.InquiryEntity;
import com.clanone.onedayclan.customer.adapter.out.persistence.repository.InquiryAnswerRepository;
import com.clanone.onedayclan.customer.adapter.out.persistence.repository.InquiryRepository;
import com.clanone.onedayclan.customer.application.exception.InquiryNotFoundException;
import com.clanone.onedayclan.customer.application.port.out.GetInquiryPort;
import com.clanone.onedayclan.customer.application.port.out.ManageInquiryPort;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import com.clanone.onedayclan.member.adapter.out.persistence.repository.MemberEntityRepository;
import com.clanone.onedayclan.member.application.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InquiryAdapter implements ManageInquiryPort, GetInquiryPort {

    private final InquiryRepository inquiryRepository;
    private final InquiryAnswerRepository inquiryAnswerRepository;
    private final MemberEntityRepository memberEntityRepository;

    @Override
    public void saveInquiry(PostInquiryRequest inquiryRequest, String userId) {
        MemberEntity member = memberEntityRepository.findByUserId(userId).orElseThrow(() -> {
            throw new MemberNotFoundException();
        });

        InquiryEntity inquiryEntity = InquiryEntity.builder()
                .title(inquiryRequest.getTitle())
                .content(inquiryRequest.getContent())
                .answerYn(false)
                .member(member)
                .build();
        inquiryRepository.save(inquiryEntity);
    }

    @Override
    public void saveInquiryAnswer(InquiryAnswerCreateRequest request, InquiryEntity inquiry) {
        inquiryAnswerRepository.save(InquiryAnswerEntity.builder()
                .inquiry(inquiry)
                .content(request.getAnswer())
                .build());
    }

    @Override
    public void deleteInquiryAnswer(long inquirySeq) {
        inquiryAnswerRepository.deleteAllByInquirySeq(inquirySeq);
    }

    @Override
    public InquiryDto getInquiryDto(String userId, long seq) {
        InquiryEntity inquiry = inquiryRepository.findByMemberUserIdAndSeqAndDeleteYn(userId, seq,false).orElseThrow(() -> {
            throw new InquiryNotFoundException();
        });

        return InquiryDto.builder()
                .title(inquiry.getTitle())
                .content(inquiry.getContent())
                .answerYn(inquiry.isAnswerYn())
                .createdAt(inquiry.getCreatedAt())
                .build();
    }

    @Override
    public List<InquiryAnswerResponse> getInquiryAnswer(long seq) {
        return inquiryAnswerRepository.findByInquirySeq(seq)
                .stream().map(InquiryAnswerResponse::of).collect(Collectors.toList());
    }

    @Override
    public List<InquiryListResponse> getInquiryList(String userId) {
        return inquiryRepository.findByMemberUserIdAndDeleteYn(userId,false)
                .stream().map(InquiryListResponse::of).collect(Collectors.toList());
    }

    @Override
    public InquiryEntity getInquiry(long seq) {
        return inquiryRepository.findBySeqAndDeleteYn(seq,false).orElseThrow(() -> {
            throw new InquiryNotFoundException();
        });
    }

    @Override
    public Page<AdminInquiryResponse> getInquiryListForAdmin(InquirySearchModel model, Pageable pageable) {
        return inquiryRepository.getInquiryListForAdmin(model, pageable);
    }

    @Override
    public AdminInquiryDetailResponse getInquiryForAdmin(long inquirySeq) {
        InquiryEntity inquiry = inquiryRepository.findById(inquirySeq).orElseThrow(() -> {throw new InquiryNotFoundException();});
        if(inquiry.isAnswerYn()) {
            List<InquiryAnswerEntity> answerList = inquiryAnswerRepository.findByInquirySeq(inquirySeq);
            return AdminInquiryDetailResponse.of(inquiry, answerList.get(0).getContent());
        }
        return AdminInquiryDetailResponse.of(inquiry, null);
    }
}
