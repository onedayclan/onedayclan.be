package com.clanone.onedayclan.clazz.adapter.out.persistence.repository;

import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassResponse;
import com.clanone.onedayclan.clazz.adapter.out.persistence.model.ClassSearchModel;
import com.clanone.onedayclan.clazz.domain.enums.ClassStatus;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.jsonwebtoken.lang.Collections;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.clanone.onedayclan.clazz.adapter.out.persistence.entity.QClassEntity.classEntity;
import static com.clanone.onedayclan.clazz.adapter.out.persistence.entity.QClassMemberEntity.classMemberEntity;
import static org.hibernate.internal.util.StringHelper.isEmpty;

@Repository
@AllArgsConstructor
public class ClassCustomRepositoryImpl implements ClassCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<AdminClassResponse> searchMemberList(ClassSearchModel optionModel, Pageable pageable) {
        List<AdminClassResponse> result = jpaQueryFactory.select(Projections.fields(AdminClassResponse.class,
                classEntity.seq,
                classEntity.thumbnail.url.as("thumbnailUrl"),
                classEntity.category.name.as("category"),
                classEntity.name,
                ExpressionUtils.as(
                        JPAExpressions.select(classMemberEntity.seq.count())
                                .from(classMemberEntity)
                                .where(classMemberEntity.clazz.seq.eq(classEntity.seq)),
                        "applicationPeople"),
                classEntity.limitPeople,
                classEntity.showYn,
                classEntity.status,
                classEntity.createdAt
                ))
                .from(classEntity)
                .where(betweenCreatedAt(optionModel.getCreatedStartAt(), optionModel.getCreatedEndAt()),
                        containName(optionModel.getName()),
                        inStatus(optionModel.getStatus()),
                        eqShowYn(optionModel.getShowYn()),
                        eqCategorySeq(optionModel.getCategorySeq()))
                .orderBy(classEntity.seq.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long count = jpaQueryFactory.select(classEntity.count())
                .from(classEntity)
                .leftJoin(classMemberEntity).on(classEntity.seq.eq(classMemberEntity.clazz.seq))
                .where(betweenCreatedAt(optionModel.getCreatedStartAt(), optionModel.getCreatedEndAt()),
                        containName(optionModel.getName()),
                        inStatus(optionModel.getStatus()),
                        eqShowYn(optionModel.getShowYn()),
                        eqCategorySeq(optionModel.getCategorySeq()))
                .fetchOne();
        return new PageImpl<>(result,pageable,count);
    }

    private BooleanExpression containName(String name) {
        return isEmpty(name) ? null : classEntity.name.contains(name);
    }

    private BooleanExpression inStatus(List<ClassStatus> status) {
        return Collections.isEmpty(status) ? null : classEntity.status.in(status);
    }

    private BooleanExpression eqShowYn(Boolean showYn) {
        return Objects.isNull(showYn) ? null : classEntity.showYn.eq(showYn);
    }

    private BooleanExpression eqCategorySeq(Long categorySeq) {
        return Objects.isNull(categorySeq) ? null : classEntity.category.seq.eq(categorySeq);
    }

    private BooleanExpression betweenCreatedAt(LocalDateTime from, LocalDateTime to) {
        return (from != null && to != null) ? classEntity.createdAt.between(from, to) : null;
    }
}
