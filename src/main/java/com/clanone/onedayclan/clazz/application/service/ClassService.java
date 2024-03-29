package com.clanone.onedayclan.clazz.application.service;

import com.clanone.onedayclan.clazz.adapter.in.web.request.*;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassCancelMemberRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassCreateRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassSearchRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassUpdateRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.response.*;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.*;
import com.clanone.onedayclan.clazz.adapter.out.persistence.model.ClassMemberSearchModel;
import com.clanone.onedayclan.clazz.adapter.out.persistence.model.ClassSearchModel;
import com.clanone.onedayclan.clazz.application.port.in.ClassPort;
import com.clanone.onedayclan.clazz.application.port.out.GetClassMemberPort;
import com.clanone.onedayclan.clazz.application.port.out.GetClassPort;
import com.clanone.onedayclan.clazz.application.port.out.ManageClassPort;
import com.clanone.onedayclan.clazz.application.service.exception.ClassAlreadyApplyException;
import com.clanone.onedayclan.clazz.application.service.exception.ClassNotApplicationException;
import com.clanone.onedayclan.clazz.domain.enums.AttendanceCheck;
import com.clanone.onedayclan.clazz.domain.enums.ClassStatus;
import com.clanone.onedayclan.common.adapter.out.persistence.entity.ImageEntity;
import com.clanone.onedayclan.common.application.port.out.ImagePort;
import com.clanone.onedayclan.member.adapter.out.persistence.entity.MemberEntity;
import com.clanone.onedayclan.member.application.port.out.GetMemberPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassService implements ClassPort {

    private final ManageClassPort classManagePort;
    private final GetClassPort getClassPort;
    private final GetClassMemberPort getClassMemberPort;
    private final ImagePort imagePort;
    private final GetMemberPort getMemberPort;

    @Override
    public AdminClassDetailResponse insertClass(AdminClassCreateRequest request) {
        // 클래스 카테고리 정보 조회
        ClassCategoryEntity category = getClassPort.getClassCategory(request.getCategorySeq());

        // 썸네일 이미지 정보 조회
        ImageEntity thumbnailImage = imagePort.getImage(request.getThumbnailSeq());

        // 클래스 정보 등록
        ClassEntity classInfo = classManagePort.insertClass(ClassEntity.of(request, category, thumbnailImage));

        List<ClassTagEntity> tagList = null;
        if(Objects.nonNull(request.getTag())) {
            // 클래스 태그 정보 등록
            tagList = Arrays.stream(request.getTag().split(","))
                    .map(s -> ClassTagEntity.of(s, classInfo))
                    .collect(Collectors.toList());

            classManagePort.insertClassTagList(tagList);
        }


        return AdminClassDetailResponse.of(classInfo, tagList);
    }

    @Override
    public AdminClassDetailResponse getClass(long classSeq) {
        ClassEntity classInfo = getClassPort.getClass(classSeq);
        List<ClassTagEntity> tagList = getClassPort.getClassTagList(classSeq);
        return AdminClassDetailResponse.of(classInfo, tagList);
    }

    @Override
    @Transactional
    public AdminClassDetailResponse updateClass(long classSeq, AdminClassUpdateRequest request) {
        // 클래스 정보 조회
        ClassEntity classInfo = getClassPort.getClass(classSeq);

        // 카테고리 정보 조회
        ClassCategoryEntity category = getClassPort.getClassCategory(request.getCategorySeq());

        // 썸네일 이미지 정보 조회
        ImageEntity thumbnail = Objects.isNull(request.getThumbnailSeq()) ? null : imagePort.getImage(request.getThumbnailSeq());

        classInfo.update(request, category, thumbnail);

        List<ClassTagEntity> tagList = null;
        if(Objects.nonNull(request.getTag())) {
            // 클래스 태그 정보 등록
            tagList = Arrays.stream(request.getTag().split(","))
                    .map(s -> ClassTagEntity.of(s, classInfo))
                    .collect(Collectors.toList());

            classManagePort.deleteClassTagList(classSeq);
            classManagePort.insertClassTagList(tagList);
        }

        return AdminClassDetailResponse.of(classInfo, tagList);
    }

    @Override
    @Transactional
    public void cancelClassMember(long classSeq, AdminClassCancelMemberRequest request) {
        ClassMemberEntity classMember = getClassPort.getClassMember(classSeq, request.getMemberSeq());
        classMember.cancel(request.getCancelReason());
    }

    @Override
    public String getCancelMessageClassMember(long classSeq, long memberSeq) {
        ClassMemberEntity classMember = getClassPort.getClassMember(classSeq, memberSeq);
        return classMember.getCancelMessage();
    }

    @Override
    @Transactional
    public void finishClass(long classSeq) {
        ClassEntity classInfo = getClassPort.getClass(classSeq);
        classInfo.finish();
    }

    @Override
    @Transactional
    public void attendanceClassMember(long classSeq, long memberSeq) {
        ClassMemberEntity classMember = getClassPort.getClassMember(classSeq, memberSeq);
        classMember.attendance();
    }

    @Override
    @Transactional
    public void absentClassMember(long classSeq, long memberSeq) {
        ClassMemberEntity classMember = getClassPort.getClassMember(classSeq, memberSeq);
        classMember.absent();
    }

    @Override
    public AdminClassCopyResponse copyClass(long classSeq) {
        ClassEntity classInfo = getClassPort.getClass(classSeq);
        List<ClassTagEntity> tagList = getClassPort.getClassTagList(classSeq);
        return AdminClassCopyResponse.of(classInfo, tagList);
    }

    @Override
    public Page<AdminClassResponse> searchClassList(AdminClassSearchRequest request, Pageable pageable) {
        return getClassPort.searchClassList(ClassSearchModel.of(request), pageable);
    }
    
    @Override
    public List<LatestClassResponse> getLatestClass() {
        return getClassPort.getFiveLatestClass().stream().map(LatestClassResponse::of).collect(Collectors.toList());
    }

    @Override
    public Page<ClassListResponse> getMainClassList(ClassSearchRequest classSearchRequest, Pageable pageable) {
        return getClassPort.getMainClassList(classSearchRequest, pageable);
    }

    @Override
    public Page<AdminClassMemberResponse> getClassMemberList(long classSeq, Pageable pageable) {
        Page<ClassMemberEntity> pageClassMemberList = getClassPort.getClassMemberList(classSeq, pageable);
        List<AdminClassMemberResponse> classMemberList = pageClassMemberList.getContent().stream().map(AdminClassMemberResponse::of).collect(Collectors.toList());
        return new PageImpl<>(classMemberList,pageable,pageClassMemberList.getTotalElements());
    }

    @Override
    public Page<AdminClassMemberListResponse> searchClassMemberList(AdminClassMemberSearchRequest request, Pageable pageable) {
        return getClassPort.searchClassMemberList(ClassMemberSearchModel.of(request), pageable);
    }

    @Override
    public Page<AdminClassReviewResponse> searchClassReviewList(AdminClassReviewSearchRequest request, Pageable pageable) {
        //클래스 목록 조회
        Page<AdminClassInfoResponse> classListPage = getClassPort.getClassInfoList(ClassSearchModel.of(request), pageable);
        List<AdminClassInfoResponse> classList = classListPage.getContent();

        //리뷰 조회
        Map<Long, AdminClassReviewInfoResponse> reviewMap = getClassPort.getClassReviewInfoList(classList.stream().map(AdminClassInfoResponse::getSeq).collect(Collectors.toList()))
                .stream()
                .collect(Collectors.toMap(AdminClassReviewInfoResponse::getSeq, i -> i));

        //클래스, 리뷰 정보 조합
        List<AdminClassReviewResponse> reviewResponseList = classList.stream()
                .map(c -> AdminClassReviewResponse.of(c, reviewMap.get(c.getSeq())))
                .collect(Collectors.toList());

        return new PageImpl<>(reviewResponseList, pageable, classListPage.getTotalElements());
    }

    @Override
    public Page<AdminClassReviewDetailResponse> getClassReviewDetail(long classSeq, Pageable pageable) {
        Page<ClassReviewEntity> reviewListPage = getClassPort.getClassReviewByClassSeq(classSeq, pageable);
        List<AdminClassReviewDetailResponse> reviewResponseList = reviewListPage.getContent().stream().map(AdminClassReviewDetailResponse::of).collect(Collectors.toList());
        return new PageImpl<>(reviewResponseList, pageable, reviewListPage.getTotalElements());
    }
    
    @Override
    public ClassDetailResponse getClassDetail(long classSeq) {
        return getClassPort.getClassDetail(classSeq);
    }

    @Override
    @Transactional
    public ApplyClassResponse applyClass(String userId, ApplyClassRequest applyClassRequest) {
        MemberEntity memberEntity = getMemberPort.getMemberByUserId(userId);
        ClassEntity classEntity = getClassPort.getClass(applyClassRequest.getSeq());

        if (classEntity.getStatus() != ClassStatus.IN_PROGRESS || LocalDateTime.now().isAfter(classEntity.getApplicationEndAt())) {
            throw new ClassNotApplicationException();
        }

        boolean alreadyApplyClass = getClassMemberPort.existsClassMember(memberEntity.getSeq(), classEntity.getSeq());

        if (alreadyApplyClass){
            throw new ClassAlreadyApplyException();
        }

        ClassMemberEntity classMemberEntity = ClassMemberEntity.builder()
                .member(memberEntity)
                .clazz(classEntity)
                .attendanceCheck(AttendanceCheck.NONE)
                .build();
        classManagePort.applyClass(classMemberEntity);

        Long classApplicationPeople = getClassMemberPort.getClassApplicationPeople(applyClassRequest.getSeq());

        if (classEntity.getLimitPeople() <= classApplicationPeople.intValue()) {
            classEntity.limitEnd();
        }

        return ApplyClassResponse.of(classEntity, classApplicationPeople);
    }

    @Override
    public List<ClassCategoryListResponse> getClassCategoryList() {
        return getClassPort.getClassCategoryList().stream().map(ClassCategoryListResponse::of).collect(Collectors.toList());
    }
}
