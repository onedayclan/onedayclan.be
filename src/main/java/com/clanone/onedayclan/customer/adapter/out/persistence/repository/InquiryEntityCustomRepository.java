package com.clanone.onedayclan.customer.adapter.out.persistence.repository;

import com.clanone.onedayclan.customer.adapter.in.web.response.*;
import com.clanone.onedayclan.customer.adapter.in.web.response.QInquiryAnswerResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.QInquiryDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.clanone.onedayclan.customer.adapter.out.persistence.entity.QInquiryAnswerEntity.inquiryAnswerEntity;
import static com.clanone.onedayclan.customer.adapter.out.persistence.entity.QInquiryEntity.inquiryEntity;

@Repository
@AllArgsConstructor
public class InquiryEntityCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public InquiryDto getInquiry(String userId, Long seq) {
        return jpaQueryFactory.select(
                        new QInquiryDto(
                                inquiryEntity.title,
                                inquiryEntity.content,
                                inquiryEntity.answerYn,
                                inquiryEntity.createdAt
                        )
                )
                .from(inquiryEntity)
                .where(
                        inquiryEntity.member.userId.eq(userId),
                        inquiryEntity.seq.eq(seq)
                )
                .fetchOne();
    }

    public List<InquiryAnswerResponse> getInquiryAnswer(Long seq){
        return jpaQueryFactory.select(
                new QInquiryAnswerResponse(
                        inquiryAnswerEntity.seq,
                        inquiryAnswerEntity.content,
                        inquiryAnswerEntity.createdAt
                    )
                )
                .from(inquiryAnswerEntity)
                .where(inquiryAnswerEntity.inquiry.seq.eq(seq))
                .fetch();
    }
}
