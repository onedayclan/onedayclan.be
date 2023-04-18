package com.clanone.onedayclan.clazz.adapter.out.persistence.repository;

import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassMemberListResponse;
import com.clanone.onedayclan.clazz.adapter.out.persistence.model.ClassMemberSearchModel;
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

import static com.clanone.onedayclan.clazz.adapter.out.persistence.entity.QClassMemberEntity.classMemberEntity;
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
