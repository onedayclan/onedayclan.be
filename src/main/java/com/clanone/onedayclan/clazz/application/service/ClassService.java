package com.clanone.onedayclan.clazz.application.service;

import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassCancelMemberRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassCreateRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassSearchRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassUpdateRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassCopyResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassDetailResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassResponse;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassCategoryEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassMemberEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassTagEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.model.ClassSearchModel;
import com.clanone.onedayclan.clazz.adapter.in.web.response.LatestClassResponse;
import com.clanone.onedayclan.clazz.application.port.in.ClassPort;
import com.clanone.onedayclan.clazz.application.port.out.GetClassPort;
import com.clanone.onedayclan.clazz.application.port.out.ManageClassPort;
import com.clanone.onedayclan.common.adapter.out.persistence.entity.ImageEntity;
import com.clanone.onedayclan.common.application.port.out.ImagePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    private final ImagePort imagePort;


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
}
