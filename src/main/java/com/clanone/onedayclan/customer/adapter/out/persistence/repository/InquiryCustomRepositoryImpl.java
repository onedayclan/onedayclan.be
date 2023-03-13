package com.clanone.onedayclan.customer.adapter.out.persistence.repository;

import com.clanone.onedayclan.customer.adapter.in.web.response.AdminInquiryResponse;
import com.clanone.onedayclan.customer.adapter.out.model.InquirySearchModel;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static com.clanone.onedayclan.customer.adapter.out.persistence.entity.QInquiryEntity.inquiryEntity;
import static com.clanone.onedayclan.member.adapter.out.persistence.entity.QMemberEntity.memberEntity;
import static org.hibernate.internal.util.StringHelper.isEmpty;

@AllArgsConstructor
public class InquiryCustomRepositoryImpl implements InquiryCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<AdminInquiryResponse> getInquiryListForAdmin(InquirySearchModel model, Pageable pageable) {
        List<AdminInquiryResponse> result = jpaQueryFactory.select(Projections.fields(AdminInquiryResponse.class,
                inquiryEntity.seq,
                inquiryEntity.title,
                inquiryEntity.content,
                inquiryEntity.answerYn,
                inquiryEntity.deleteYn,
                inquiryEntity.createdAt,
                memberEntity.name,
                memberEntity.userId))
                .from(inquiryEntity)
                .innerJoin(memberEntity).on(inquiryEntity.member.seq.eq(memberEntity.seq))
                .where(
                        containsUserId(model.getUserId()),
                        containsUserName(model.getName()),
                        eqAnswerYn(model.getAnswerYn()),
                        eqDeleteYn(model.getDeleteYn()),
                        betweenCreatedAt(model.getSearchStartAt(), model.getSearchEndAt())
                )
                .fetch();
        long count = jpaQueryFactory.select(inquiryEntity.count())
                .from(inquiryEntity)
                .innerJoin(memberEntity).on(inquiryEntity.member.seq.eq(memberEntity.seq))
                .where(
                        containsUserId(model.getUserId()),
                        containsUserName(model.getName()),
                        eqAnswerYn(model.getAnswerYn()),
                        eqDeleteYn(model.getDeleteYn()),
                        betweenCreatedAt(model.getSearchStartAt(), model.getSearchEndAt())
                )
                .fetchOne();
        return new PageImpl<>(result, pageable, count);
    }

    private BooleanExpression containsUserId(String userId) {
        return isEmpty(userId) ? null : memberEntity.userId.contains(userId);
    }

    private BooleanExpression containsUserName(String name) {
        return isEmpty(name) ? null : memberEntity.name.contains(name);
    }

    private BooleanExpression eqAnswerYn(Boolean answerYn) {
        return answerYn == null ? null : inquiryEntity.answerYn.eq(answerYn);
    }

    private BooleanExpression eqDeleteYn(Boolean deleteYn) {
        return deleteYn == null ? null : inquiryEntity.deleteYn.eq(deleteYn);
    }

    private BooleanExpression betweenCreatedAt(LocalDateTime from, LocalDateTime to) {
        return (from != null && to != null) ? inquiryEntity.createdAt.between(from, to) : null;
    }
}
