package com.clanone.onedayclan.clazz.application.port.in;

import com.clanone.onedayclan.clazz.adapter.in.web.request.*;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassCancelMemberRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassCreateRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassSearchRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassUpdateRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClassPort {
    AdminClassDetailResponse insertClass(AdminClassCreateRequest request);
    AdminClassDetailResponse getClass(long classSeq);
    AdminClassDetailResponse updateClass(long classSeq, AdminClassUpdateRequest request);
    void cancelClassMember(long classSeq, AdminClassCancelMemberRequest request);
    String getCancelMessageClassMember(long classSeq, long memberSeq);
    void finishClass(long classSeq);
    void attendanceClassMember(long classSeq, long memberSeq);
    void absentClassMember(long classSeq, long memberSeq);
    AdminClassCopyResponse copyClass(long classSeq);
    Page<AdminClassResponse> searchClassList(AdminClassSearchRequest request, Pageable pageable);
    List<LatestClassResponse> getLatestClass();
    Page<ClassListResponse> getMainClassList(ClassSearchRequest classSearchRequest, Pageable pageable);
    Page<AdminClassMemberResponse> getClassMemberList(long classSeq, Pageable pageable);
    Page<AdminClassMemberListResponse> searchClassMemberList(AdminClassMemberSearchRequest request, Pageable pageable);
    Page<AdminClassReviewResponse> searchClassReviewList(AdminClassReviewSearchRequest request, Pageable pageable);
    Page<AdminClassReviewDetailResponse> getClassReviewDetail(long classSeq, Pageable pageable);
    ClassDetailResponse getClassDetail(long classSeq);
    ApplyClassResponse applyClass(String userId, ApplyClassRequest applyClassRequest);
}
