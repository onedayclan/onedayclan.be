package com.clanone.onedayclan.clazz.adapter.out.persistence.repository;

import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassSearchRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.request.ClassSearchRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.response.ClassDetailResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.response.ClassListResponse;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.QClassEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.model.ClassSearchModel;
import com.clanone.onedayclan.clazz.domain.enums.ClassListSort;
import com.clanone.onedayclan.clazz.domain.enums.ClassStatus;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.jsonwebtoken.lang.Collections;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.clanone.onedayclan.clazz.adapter.out.persistence.entity.QClassEntity.classEntity;
import static com.clanone.onedayclan.clazz.adapter.out.persistence.entity.QClassMemberEntity.classMemberEntity;
import static com.clanone.onedayclan.clazz.adapter.out.persistence.entity.QClassTagEntity.classTagEntity;
import static com.clanone.onedayclan.member.adapter.out.persistence.entity.QOrganizationEntity.organizationEntity;
import static org.hibernate.internal.util.StringHelper.isEmpty;
import static org.springframework.util.StringUtils.hasText;

@Repository
@AllArgsConstructor
public class ClassCustomRepositoryImpl implements ClassCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

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

    @Override
    public Page<ClassListResponse> searchClassList(ClassSearchRequest optionModel, Pageable pageable) {
        // 거리순 정렬때문에 jpa 사용 불가능 -> nativeQuery 사용함.

        String selectElement = "select class.seq as seq, class.name as name, class.start_at as start_at, " +
                "class.description as description, class.organization_fee as organization_fee, " +
                "class.normal_fee as normal_fee, class.status as status, image.url as thumnaul_url, " +
                "class_category.name as category_name, ";

        double myLongitude = optionModel.getLongitude();
        double myLatitude = optionModel.getLatitude();

        String distanceSort =
                "(6371 * acos(cos(CAST(class.latitude AS FLOAT) * 3.141592653589793 / 180.0) * cos(" + myLatitude + "* 3.141592653589793 / 180.0)" +
                        "* cos((" + myLongitude + "* 3.141592653589793 / 180.0) - (CAST(class.longitude AS FLOAT) * 3.141592653589793 / 180.0)) + sin(CAST(class.latitude AS FLOAT) * 3.141592653589793 / 180.0)" +
                        "* sin(" + myLatitude + "* 3.141592653589793 / 180.0))) as distance ";

        String fromClassInnerJoin = "from class join image on image.seq=class.thumbnail_seq " +
                "join class_category on class_category.seq=class.category_seq " +
                "left join class_tag on class.seq = class_tag.class_seq ";

        String whereCondition = "";
        if (Objects.nonNull(optionModel.getCategorySeq())) {
            whereCondition = "where class.category_seq = " + Long.toString(optionModel.getCategorySeq()) + " ";
        }
        if (!isEmpty(optionModel.getSearchKeyword())){
            String keyword = "'%" + optionModel.getSearchKeyword() + "%'";
            if (!isEmpty(whereCondition)) {
                whereCondition = "and ";
            }
            whereCondition = "where (class.name like " + keyword + " escape '!' or class.description like " + keyword + " escape '!' or class_tag.name like " + keyword + " )";
        }

        String sortCondition = "";
        if (optionModel.getSort() == ClassListSort.NEW) {
            sortCondition = "order by class.seq desc ";
        } else {
            sortCondition = "order by distance asc ";
        }

        String limitCondition = "limit " + pageable.getOffset() + "," + pageable.getPageSize();

        String sql =
                selectElement + distanceSort + fromClassInnerJoin + whereCondition + "group by class.seq " + sortCondition + limitCondition;

        Query nativeQuery = em.createNativeQuery(sql);
        List<Object[]> resultList = nativeQuery.getResultList();

        List<ClassListResponse> classListResponse = new ArrayList<>();
        for (Object[] o : resultList) {
            String format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format((Timestamp) o[2]);
            ClassStatus classStatus = ClassStatus.valueOf((String) o[6]);

            classListResponse.add(new ClassListResponse((long) o[0], (String) o[1], format, (String) o[3], (Integer) o[4],
                    (Integer) o[5], classStatus, (String) o[7], (String) o[8]));
        }

        long count = jpaQueryFactory.select(classEntity.seq).distinct()
                .from(classEntity)
                .where(eqCategory(optionModel.getCategorySeq()),eqSearch(optionModel.getSearchKeyword()))
                .leftJoin(classTagEntity).on(classTagEntity.clazz.seq.eq(classEntity.seq))
                .fetch().size();

        return new PageImpl<>(classListResponse, pageable, count);
    }

    @Override
    public ClassDetailResponse getClassDetail(long classSeq) {
        return jpaQueryFactory.select(Projections.fields(ClassDetailResponse.class,
                        classEntity.seq,
                        classEntity.thumbnail.fileName,
                        classEntity.name,
                        classEntity.category.name.as("category"),
                        classEntity.status,
                        classEntity.teacherName,
                        classEntity.startAt,
                        classEntity.endAt,
                        classEntity.limitPeople,
                        ExpressionUtils.as(
                                JPAExpressions.select(classMemberEntity.seq.count())
                                        .from(classMemberEntity)
                                        .where(classMemberEntity.clazz.seq.eq(classEntity.seq)),
                                "applicationPeople"),
                        classEntity.organizationFee,
                        classEntity.organizationFee,
                        classEntity.applicationEndAt,
                        classEntity.offlineYn,
                        classEntity.offlineLink,
                        classEntity.description,
                        classEntity.progress,
                        classEntity.longitude,
                        classEntity.latitude,
                        classEntity.location,
                        classEntity.locationDetail
                ))
                .from(classEntity)
                .where(classEntity.seq.eq(classSeq))
                .fetchOne();
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

    private BooleanExpression eqCategory(Long categorySeq) {
        return Objects.isNull(categorySeq) ? null : classEntity.category.seq.eq(categorySeq);
    }

    private BooleanExpression eqSearch(String search){
        return hasText(search) ? classEntity.name.contains(search).or(classEntity.description.contains(search)).or(classTagEntity.name.contains(search))
                : null;
    }
}
