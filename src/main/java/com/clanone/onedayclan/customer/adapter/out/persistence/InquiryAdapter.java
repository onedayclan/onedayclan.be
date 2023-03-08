package com.clanone.onedayclan.customer.adapter.out.persistence;

import com.clanone.onedayclan.customer.adapter.in.web.request.PostInquiryRequest;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.InquiryEntity;
import com.clanone.onedayclan.customer.adapter.out.persistence.repository.InquiryRepository;
import com.clanone.onedayclan.customer.application.port.out.SaveInquiryPort;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import com.clanone.onedayclan.member.adapter.out.persistence.repository.MemberEntityRepository;
import com.clanone.onedayclan.member.application.exception.MemberNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class InquiryAdapter implements SaveInquiryPort{

    private final InquiryRepository inquiryRepository;
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
}
