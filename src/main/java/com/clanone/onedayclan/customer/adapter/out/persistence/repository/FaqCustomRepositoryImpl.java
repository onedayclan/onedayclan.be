package com.clanone.onedayclan.customer.adapter.out.persistence.repository;

import com.clanone.onedayclan.customer.adapter.out.model.FaqSearchModel;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.FaqEntity;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.QFaqEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static com.clanone.onedayclan.customer.adapter.out.persistence.entity.QFaqEntity.faqEntity;
import static com.clanone.onedayclan.member.adapter.out.persistence.entity.QMemberEntity.memberEntity;
import static org.hibernate.internal.util.StringHelper.isEmpty;

@RequiredArgsConstructor
public class FaqCustomRepositoryImpl implements FaqCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<FaqEntity> getFaqListForAdmin(FaqSearchModel model, Pageable pageable) {
        List<FaqEntity> faqList = jpaQueryFactory.selectFrom(faqEntity)
                .where(
                        containsTitle(model.getTitle()),
                        betweenCreatedAt(model.getSearchStartAt(), model.getSearchEndAt())
                )
                .orderBy(faqEntity.seq.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long count = jpaQueryFactory.select(faqEntity.count())
                .from(faqEntity)
                .where(
                        containsTitle(model.getTitle()),
                        betweenCreatedAt(model.getSearchStartAt(), model.getSearchEndAt())
                )
                .fetchOne();
        return new PageImpl<>(faqList, pageable, count);
    }
    private BooleanExpression containsTitle(String title) {
        return isEmpty(title) ? null : faqEntity.title.contains(title);
    }

    private BooleanExpression betweenCreatedAt(LocalDateTime from, LocalDateTime to) {
        return (from != null && to != null) ? faqEntity.createdAt.between(from, to) : null;
    }
}
