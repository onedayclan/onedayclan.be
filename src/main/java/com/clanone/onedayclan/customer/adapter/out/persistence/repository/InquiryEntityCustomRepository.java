package com.clanone.onedayclan.customer.adapter.out.persistence.repository;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.InquiryAnswerEntity;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.InquiryEntity;
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

    public InquiryEntity getInquiry(String userId, Long seq) {
        return jpaQueryFactory.selectFrom(inquiryEntity)
                .where(
                        inquiryEntity.member.userId.eq(userId),
                        inquiryEntity.seq.eq(seq)
                )
                .fetchOne();
    }

    public List<InquiryAnswerEntity> getInquiryAnswer(Long seq){
        return jpaQueryFactory.selectFrom(inquiryAnswerEntity)
                .where(inquiryAnswerEntity.inquiry.seq.eq(seq))
                .fetch();
    }
}