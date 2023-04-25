package com.clanone.onedayclan.clazz.adapter.out.persistence.repository;

import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassReviewInfoResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.clanone.onedayclan.clazz.adapter.out.persistence.entity.QClassReviewEntity.classReviewEntity;

@Repository
@RequiredArgsConstructor
public class ClassReviewCustomRepositoryImpl implements ClassReviewCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<AdminClassReviewInfoResponse> getReviewInfoByClassSeq(List<Long> classSeqList) {
        return jpaQueryFactory.select(Projections.fields(AdminClassReviewInfoResponse.class,
                classReviewEntity.clazz.seq,
                classReviewEntity.seq.count().as("reviewCount"),
                classReviewEntity.star.avg().as("reviewScore")
                ))
                .from(classReviewEntity)
                .where(inClassSeq(classSeqList))
                .groupBy(classReviewEntity.clazz.seq)
                .fetch();
    }

    private BooleanExpression inClassSeq(List<Long> classSeq) {
        return classReviewEntity.clazz.seq.in(classSeq);
    }
}
