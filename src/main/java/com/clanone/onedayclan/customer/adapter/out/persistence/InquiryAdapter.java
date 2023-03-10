package com.clanone.onedayclan.customer.adapter.out.persistence;

import com.clanone.onedayclan.customer.adapter.in.web.request.PostInquiryRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryAnswerResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryDto;
import com.clanone.onedayclan.customer.adapter.in.web.response.InquiryListResponse;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.InquiryEntity;
import com.clanone.onedayclan.customer.adapter.out.persistence.repository.InquiryEntityCustomRepository;
import com.clanone.onedayclan.customer.adapter.out.persistence.repository.InquiryRepository;
import com.clanone.onedayclan.customer.application.port.out.GetInquiryPort;
import com.clanone.onedayclan.customer.application.port.out.SaveInquiryPort;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import com.clanone.onedayclan.member.adapter.out.persistence.repository.MemberEntityRepository;
import com.clanone.onedayclan.member.application.exception.MemberNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetInquiryAdapter implements SaveInquiryPort, GetInquiryPort {

    private final InquiryRepository inquiryRepository;
    private final MemberEntityRepository memberEntityRepository;
    private final InquiryEntityCustomRepository inquiryEntityCustomRepository;

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
    public InquiryDto getInquiry(String userId, Long seq) {
        InquiryEntity inquiry = inquiryEntityCustomRepository.getInquiry(userId, seq);
        return InquiryDto.builder()
                .title(inquiry.getTitle())
                .content(inquiry.getContent())
                .answerYn(inquiry.isAnswerYn())
                .createdAt(inquiry.getCreatedAt())
                .build();
    }

    @Override
    public List<InquiryAnswerResponse> getInquiryAnswer(Long seq) {
        return inquiryEntityCustomRepository.getInquiryAnswer(seq)
                .stream().map(InquiryAnswerResponse::of).collect(Collectors.toList());
    }

    @Override
    public List<InquiryListResponse> getInquiryList(String userId) {
        return inquiryRepository.findByMemberUserId(userId)
                .stream().map(InquiryListResponse::of).collect(Collectors.toList());
    }
}
