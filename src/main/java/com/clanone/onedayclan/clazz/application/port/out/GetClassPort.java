package com.clanone.onedayclan.clazz.application.port.out;

import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassMemberListResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.request.ClassSearchRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.response.ClassDetailResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.response.ClassListResponse;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassCategoryEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassMemberEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassTagEntity;
import com.clanone.onedayclan.clazz.adapter.out.persistence.model.ClassMemberSearchModel;
import com.clanone.onedayclan.clazz.adapter.out.persistence.model.ClassSearchModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GetClassPort {
    ClassCategoryEntity getClassCategory(long categorySeq);
    ClassEntity getClass(long classSeq);
    List<ClassTagEntity> getClassTagList(long classSeq);
    ClassMemberEntity getClassMember(long classSeq, long memberSeq);
    Page<AdminClassResponse> searchClassList(ClassSearchModel optionModel, Pageable pageable);
    List<ClassEntity> getFiveLatestClass();
    Page<ClassListResponse> getMainClassList(ClassSearchRequest classSearchRequest, Pageable pageable);
    Page<ClassMemberEntity> getClassMemberList(long classSeq, Pageable pageable);
    Page<AdminClassMemberListResponse> searchClassMemberList(ClassMemberSearchModel optionModel, Pageable pageable);
    ClassDetailResponse getClassDetail(long classSeq);
}
