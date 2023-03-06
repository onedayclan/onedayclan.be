package com.clanone.onedayclan.member.adapter.out.persistence.repository;

import com.clanone.onedayclan.member.adapter.in.web.response.MemberSearchResponse;
import com.clanone.onedayclan.member.adapter.out.model.MemberSearchModel;
import com.clanone.onedayclan.member.domain.enums.MemberStatusType;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.clanone.onedayclan.member.adapter.out.persistence.entity.QMemberEntity.memberEntity;
import static com.clanone.onedayclan.member.adapter.out.persistence.entity.QOrganizationEntity.organizationEntity;
import static org.hibernate.internal.util.StringHelper.isEmpty;

@Repository
@AllArgsConstructor
public class MemberEntityCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public Page<MemberSearchResponse> findMember(MemberSearchModel optionModel, Pageable pageable) {
        List<MemberSearchResponse> result = jpaQueryFactory.select(Projections.fields(MemberSearchResponse.class,
                memberEntity.seq,
                        memberEntity.userId,
                        memberEntity.name,
                        organizationEntity.name.as("organization"),
                        memberEntity.organizationStatus,
                        memberEntity.createdAt,
                        memberEntity.status,
                        memberEntity.memo
                ))
                .from(memberEntity)
                .leftJoin(organizationEntity).on(organizationEntity.seq.eq(memberEntity.confirmOrganization.seq))
                .where(eqMemberUserId(optionModel.getUserId()),
                        eqMemberName(optionModel.getName()),
                        eqMemberStatus(optionModel.getStatus()),
                        betweenCreatedAt(optionModel.getSearchStartAt(), optionModel.getSearchEndAt()),
                        eqOrganizationSeq(optionModel.getOrganizationSeq())
                )
                .orderBy(memberEntity.seq.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long count = jpaQueryFactory.select(memberEntity.count())
                .from(memberEntity)
                .where(eqMemberUserId(optionModel.getUserId()),
                        eqMemberName(optionModel.getName()),
                        eqMemberStatus(optionModel.getStatus()),
                        betweenCreatedAt(optionModel.getSearchStartAt(), optionModel.getSearchEndAt()),
                        eqOrganizationSeq(optionModel.getOrganizationSeq())
                ).fetchOne();
        return new PageImpl<>(result, pageable, count);
    }

    private BooleanExpression eqMemberName(String name) {
        return isEmpty(name) ? null : memberEntity.name.eq(name);
    }

    private BooleanExpression eqMemberUserId(String userId) {
        return isEmpty(userId) ? null : memberEntity.userId.eq(userId);
    }

    private BooleanExpression eqMemberStatus(MemberStatusType status) {
        return status == null ? null : memberEntity.status.eq(status);
    }

    private BooleanExpression betweenCreatedAt(LocalDateTime from, LocalDateTime to) {
        return (from != null && to != null) ? memberEntity.createdAt.between(from, to) : null;
    }

    private BooleanExpression eqOrganizationSeq(long organizationSeq) {
        return organizationSeq == 0 ? null : organizationEntity.seq.eq(organizationSeq);
    }
}
