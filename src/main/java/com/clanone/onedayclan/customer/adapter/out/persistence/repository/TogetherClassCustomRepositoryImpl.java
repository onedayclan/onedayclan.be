package com.clanone.onedayclan.customer.adapter.out.persistence.repository;

import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTogetherClassDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTogetherClassResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.TogetherClassResponse;
import com.clanone.onedayclan.customer.adapter.out.model.TogetherClassSearchModel;
import com.clanone.onedayclan.customer.domain.enums.TogetherClassCategory;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static com.clanone.onedayclan.customer.adapter.out.persistence.entity.QNoticeEntity.noticeEntity;
import static com.clanone.onedayclan.customer.adapter.out.persistence.entity.QTogetherClassEntity.togetherClassEntity;
import static com.clanone.onedayclan.member.adapter.out.persistence.entity.QMemberEntity.memberEntity;
import static com.clanone.onedayclan.member.adapter.out.persistence.entity.QOrganizationEntity.organizationEntity;
import static org.hibernate.internal.util.StringHelper.isEmpty;

@RequiredArgsConstructor
public class TogetherClassCustomRepositoryImpl implements TogetherClassCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<AdminTogetherClassResponse> getTogetherListForAdmin(TogetherClassSearchModel model, Pageable pageable) {
        List<AdminTogetherClassResponse> togetherClassList = jpaQueryFactory.select(Projections.fields(AdminTogetherClassResponse.class,
                    togetherClassEntity.seq,
                    togetherClassEntity.title,
                    memberEntity.userId,
                    memberEntity.name,
                    togetherClassEntity.category,
                    organizationEntity.name.as("organizationName"),
                    togetherClassEntity.answer.isNotNull().as("answerYn"),
                    togetherClassEntity.createdAt
                ))
                .from(togetherClassEntity)
                .join(memberEntity).on(togetherClassEntity.member.seq.eq(memberEntity.seq))
                .leftJoin(organizationEntity).on(organizationEntity.seq.eq(memberEntity.confirmOrganization.seq))
                .where(
                        containsMemberName(model.getName()),
                        containsMemberId(model.getUserId()),
                        eqCategory(model.getCategory()),
                        eqAnswerYn(model.getAnswerYn()),
                        eqOrganizationSeq(model.getOrganizationSeq()),
                        betweenCreatedAt(model.getSearchStartAt(), model.getSearchEndAt())
                )
                .orderBy(organizationEntity.seq.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long count = jpaQueryFactory.select(togetherClassEntity.count())
                .from(togetherClassEntity)
                .join(memberEntity).on(togetherClassEntity.member.seq.eq(memberEntity.seq))
                .leftJoin(organizationEntity).on(organizationEntity.seq.eq(memberEntity.confirmOrganization.seq))
                .where(
                        containsMemberName(model.getName()),
                        containsMemberId(model.getUserId()),
                        eqCategory(model.getCategory()),
                        eqAnswerYn(model.getAnswerYn()),
                        eqOrganizationSeq(model.getOrganizationSeq()),
                        betweenCreatedAt(model.getSearchStartAt(), model.getSearchEndAt())
                )
                .fetchOne();
        return new PageImpl<>(togetherClassList, pageable, count);
    }

    @Override
    public AdminTogetherClassDetailResponse getTogetherForAdmin(long togetherClassSeq) {
        return jpaQueryFactory.select(Projections.fields(AdminTogetherClassDetailResponse.class,
                    togetherClassEntity.seq,
                    memberEntity.name,
                    memberEntity.userId,
                    togetherClassEntity.title,
                    togetherClassEntity.content,
                    togetherClassEntity.answer,
                    togetherClassEntity.createdAt,
                    togetherClassEntity.limitPeople,
                    togetherClassEntity.category,
                    organizationEntity.name.as("organizationName")
                ))
                .from(togetherClassEntity)
                .join(memberEntity).on(memberEntity.seq.eq(togetherClassEntity.member.seq))
                .leftJoin(organizationEntity).on(organizationEntity.seq.eq(memberEntity.confirmOrganization.seq))
                .where(eqTogetherClassSeq(togetherClassSeq))
                .fetchOne();
    }

    private BooleanExpression containsMemberName(String name) {
        return isEmpty(name) ? null : memberEntity.name.contains(name);
    }

    private BooleanExpression containsMemberId(String userId) {
        return isEmpty(userId) ? null : memberEntity.userId.contains(userId);
    }

    private BooleanExpression eqCategory(TogetherClassCategory category) {
        return category == null ? null : togetherClassEntity.category.eq(category);
    }

    private BooleanExpression eqAnswerYn(Boolean answerYn) {
        if(answerYn == null) {
            return null;
        }
        return answerYn == true ? togetherClassEntity.answer.isNotNull() : togetherClassEntity.answer.isNull();
    }

    private BooleanExpression eqOrganizationSeq(Long organizationSeq) {
        return organizationSeq == null ? null : organizationEntity.seq.eq(organizationSeq);
    }

    private BooleanExpression betweenCreatedAt(LocalDateTime from, LocalDateTime to) {
        return (from != null && to != null) ? togetherClassEntity.createdAt.between(from, to) : null;
    }

    private BooleanExpression eqTogetherClassSeq(long togetherClassSeq) {
        return togetherClassEntity.seq.eq(togetherClassSeq);
    }
}
