package com.clanone.onedayclan.clazz.adapter.out.persistence.repository;

import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassMemberListResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.response.CancelClassMessageResponse;
import com.clanone.onedayclan.clazz.adapter.out.persistence.model.ClassMemberSearchModel;
import com.clanone.onedayclan.clazz.application.model.ScheduledClassModel;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.clanone.onedayclan.clazz.adapter.out.persistence.entity.QClassCategoryEntity.*;
import static com.clanone.onedayclan.clazz.adapter.out.persistence.entity.QClassEntity.*;
import static com.clanone.onedayclan.clazz.adapter.out.persistence.entity.QClassMemberEntity.classMemberEntity;
import static com.clanone.onedayclan.clazz.adapter.out.persistence.entity.QClassReviewEntity.*;
import static com.clanone.onedayclan.common.adapter.out.persistence.entity.QImageEntity.*;
import static com.clanone.onedayclan.member.adapter.out.persistence.entity.QMemberEntity.*;
import static org.hibernate.internal.util.StringHelper.isEmpty;

@Repository
@RequiredArgsConstructor
public class ClassMemberCustomRepositoryImpl implements ClassMemberCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<AdminClassMemberListResponse> searchClassMemberList(ClassMemberSearchModel optionModel, Pageable pageable) {
        List<AdminClassMemberListResponse> result = jpaQueryFactory.select(Projections.fields(AdminClassMemberListResponse.class,
                classMemberEntity.seq,
                classMemberEntity.clazz.name.as("className"),
                classMemberEntity.member.userId,
                classMemberEntity.member.phone,
                classMemberEntity.member.name.as("userName"),
                classMemberEntity.clazz.category.name.as("classCategory"),
                classMemberEntity.member.confirmOrganization.name.as("organizationName"),
                classMemberEntity.createdAt
                ))
                .from(classMemberEntity)
                .where(containClassName(optionModel.getClassName()),
                        containUserId(optionModel.getUserId()),
                        eqClassCategorySeq(optionModel.getClassCategorySeq()),
                        eqOrganizationSeq(optionModel.getOrganizationSeq()),
                        betweenCreatedAt(optionModel.getCreatedStartAt(), optionModel.getCreatedEndAt()))
                .fetch();

        long count = jpaQueryFactory.select(classMemberEntity.seq.count())
                .from(classMemberEntity)
                .where(containClassName(optionModel.getClassName()),
                        containUserId(optionModel.getUserId()),
                        eqClassCategorySeq(optionModel.getClassCategorySeq()),
                        eqOrganizationSeq(optionModel.getOrganizationSeq()),
                        betweenCreatedAt(optionModel.getCreatedStartAt(), optionModel.getCreatedEndAt()))
                .fetchOne();
        return new PageImpl<>(result, pageable, count);
    }

    @Override
    public List<CancelClassMessageResponse> getCancelClassMessage(long memberSeq) {
        return jpaQueryFactory.select(Projections.fields(CancelClassMessageResponse.class,
                        classEntity.seq.as("classSeq"),
                        classEntity.name.as("className"),
                        classMemberEntity.cancelMessage.as("message")
                ))
                .from(classMemberEntity)
                .leftJoin(classEntity).on(classMemberEntity.clazz.seq.eq(classEntity.seq))
                .where(classMemberEntity.member.seq.eq(memberSeq)
                        .and(classMemberEntity.cancelYn.eq(true)))
                .orderBy(classEntity.startAt.asc())
                .fetch();
    }

    @Override
    public List<ScheduledClassModel> getScheduledClassModel(String userId) {
        return jpaQueryFactory.select(Projections.fields(ScheduledClassModel.class,
                        classEntity.seq.as("classSeq"),
                        imageEntity.url.as("thumbnailUrl"),
                        classMemberEntity.cancelYn,
                        classMemberEntity.attendanceCheck,
                        classReviewEntity.seq.as("reviewSeq"),
                        classEntity.name.as("className"),
                        classEntity.startAt.as("classStartAt"),
                        classCategoryEntity.name.as("category"),
                        memberEntity.penaltyStartAt,
                        memberEntity.penaltyEndAt
                )).from(classMemberEntity)
                .leftJoin(classReviewEntity).on(classMemberEntity.clazz.seq.eq(classReviewEntity.clazz.seq))
                .leftJoin(classEntity).on(classMemberEntity.clazz.seq.eq(classEntity.seq))
                .leftJoin(imageEntity).on(classEntity.thumbnail.seq.eq(imageEntity.seq))
                .leftJoin(classCategoryEntity).on(classEntity.category.seq.eq(classCategoryEntity.seq))
                .leftJoin(memberEntity).on(classMemberEntity.member.seq.eq(memberEntity.seq))
                .where(memberEntity.userId.eq(userId))
                .orderBy(classEntity.startAt.asc())
                .fetch();
    }

    private BooleanExpression containClassName(String className) {
        return isEmpty(className) ? null : classMemberEntity.clazz.name.contains(className);
    }

    private BooleanExpression containUserId(String userId) {
        return isEmpty(userId) ? null : classMemberEntity.member.userId.contains(userId);
    }

    private BooleanExpression eqOrganizationSeq(Long organizationSeq) {
        return Objects.isNull(organizationSeq) ? null : classMemberEntity.member.confirmOrganization.seq.eq(organizationSeq);
    }

    private BooleanExpression eqClassCategorySeq(Long classCategorySeq) {
        return Objects.isNull(classCategorySeq) ? null : classMemberEntity.clazz.category.seq.eq(classCategorySeq);
    }

    private BooleanExpression betweenCreatedAt(LocalDateTime from, LocalDateTime to) {
        return (from != null && to != null) ? classMemberEntity.createdAt.between(from, to) : null;
    }
}
