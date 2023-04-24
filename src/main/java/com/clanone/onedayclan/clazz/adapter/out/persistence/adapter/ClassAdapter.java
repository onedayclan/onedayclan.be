package com.clanone.onedayclan.clazz.adapter.out.persistence.adapter;

import com.clanone.onedayclan.clazz.adapter.in.web.response.*;
import com.clanone.onedayclan.clazz.adapter.in.web.request.ClassSearchRequest;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassCategoryEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassMemberEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassTagEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.model.ClassMemberSearchModel;
import com.clanone.onedayclan.clazz.adapter.out.persistence.model.ClassSearchModel;
import com.clanone.onedayclan.clazz.adapter.out.persistence.repository.*;
import com.clanone.onedayclan.clazz.application.port.out.GetClassPort;
import com.clanone.onedayclan.clazz.application.port.out.ManageClassPort;
import com.clanone.onedayclan.clazz.application.service.exception.ClassCategoryNotFoundException;
import com.clanone.onedayclan.clazz.application.service.exception.ClassInfoNotFoundException;
import com.clanone.onedayclan.clazz.application.service.exception.ClassMemberNotFoundException;
import com.clanone.onedayclan.clazz.domain.enums.ClassStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ClassAdapter implements ManageClassPort, GetClassPort {

    private final ClassRepository classRepository;
    private final ClassCategoryRepository classCategoryRepository;
    private final ClassTagRepository classTagRepository;
    private final ClassMemberRepository classMemberRepository;
    private final ClassReviewRepository classReviewRepository;

    @Override
    public ClassEntity insertClass(ClassEntity classEntity) {
        return classRepository.save(classEntity);
    }

    @Override
    public List<ClassTagEntity> insertClassTagList(List<ClassTagEntity> classTagList) {
        return classTagRepository.saveAll(classTagList);
    }

    @Override
    public void deleteClassTagList(long classSeq) {
        classTagRepository.deleteAllByClazzSeq(classSeq);
    }

    @Override
    public ClassCategoryEntity getClassCategory(long categorySeq) {
        return classCategoryRepository.findById(categorySeq).orElseThrow(() -> {throw new ClassCategoryNotFoundException();});
    }

    @Override
    public ClassEntity getClass(long classSeq) {
        return classRepository.findById(classSeq).orElseThrow(() -> {throw new ClassInfoNotFoundException();});
    }

    @Override
    public List<ClassTagEntity> getClassTagList(long classSeq) {
        return classTagRepository.findByClazzSeq(classSeq);
    }

    @Override
    public ClassMemberEntity getClassMember(long classSeq, long memberSeq) {
        return classMemberRepository.findByClazzSeqAndMemberSeq(classSeq, memberSeq).orElseThrow(() -> {throw new ClassMemberNotFoundException();});
    }
    
    @Override
    public Page<AdminClassResponse> searchClassList(ClassSearchModel optionModel, Pageable pageable) {
        return classRepository.searchMemberList(optionModel, pageable);
    }
    
    @Override
    public List<ClassEntity> getFiveLatestClass() {
        return classRepository.findTop5ByStatusOrderByCreatedAtDesc(ClassStatus.IN_PROGRESS);
    }

    @Override
    public Page<ClassListResponse> getMainClassList(ClassSearchRequest classSearchRequest, Pageable pageable) {
        return classRepository.searchClassList(classSearchRequest, pageable);
    }
  
     @Override
    public Page<ClassMemberEntity> getClassMemberList(long classSeq, Pageable pageable) {
        return classMemberRepository.findByClazzSeq(classSeq, pageable);

    }

    @Override
    public Page<AdminClassMemberListResponse> searchClassMemberList(ClassMemberSearchModel optionModel, Pageable pageable) {
        return classMemberRepository.searchClassMemberList(optionModel, pageable);
    }

    @Override
    public Page<AdminClassInfoResponse> getClassInfoList(ClassSearchModel optionModel, Pageable pageable) {
        return classRepository.getClassInfoList(optionModel, pageable);
    }

    @Override
    public List<AdminClassReviewInfoResponse> getClassReviewInfoList(List<Long> classSeqList) {
        return classReviewRepository.getReviewInfoByClassSeq(classSeqList);
    }


}
