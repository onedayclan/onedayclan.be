package com.clanone.onedayclan.customer.adapter.out.persistence.repository;

import com.clanone.onedayclan.customer.adapter.in.web.response.AdminNoticeResponse;
import com.clanone.onedayclan.customer.adapter.out.model.NoticeSearchModel;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static com.clanone.onedayclan.customer.adapter.out.persistence.entity.QNoticeEntity.noticeEntity;
import static org.hibernate.internal.util.StringHelper.isEmpty;

@AllArgsConstructor
public class NoticeCustomRepositoryImpl implements NoticeCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Page<AdminNoticeResponse> getNoticeListForAdmin(NoticeSearchModel model, Pageable pageable) {
        List<AdminNoticeResponse> noticeList = jpaQueryFactory.select(Projections.fields(AdminNoticeResponse.class,
                noticeEntity.seq,
                noticeEntity.title,
                noticeEntity.showYn,
                noticeEntity.createdAt
                ))
                .from(noticeEntity)
                .where(
                    containsTitle(model.getTitle()),
                    betweenCreatedAt(model.getSearchStartAt(), model.getSearchEndAt())
                )
                .orderBy(noticeEntity.seq.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long count = jpaQueryFactory
                .select(noticeEntity.count())
                .from(noticeEntity)
                .where(
                        containsTitle(model.getTitle()),
                        betweenCreatedAt(model.getSearchStartAt(), model.getSearchEndAt())
                )
                .fetchOne();

        return new PageImpl<>(noticeList, pageable, count);
    }

    private BooleanExpression containsTitle(String title) {
        return isEmpty(title) ? null : noticeEntity.title.contains(title);
    }

    private BooleanExpression betweenCreatedAt(LocalDateTime from, LocalDateTime to) {
        return (from != null && to != null) ? noticeEntity.createdAt.between(from, to) : null;
    }
}
