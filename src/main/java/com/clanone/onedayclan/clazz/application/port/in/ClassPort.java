package com.clanone.onedayclan.clazz.application.port.in;

import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassCancelMemberRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassCreateRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassUpdateRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassDetailResponse;

public interface ClassPort {
    AdminClassDetailResponse insertClass(AdminClassCreateRequest request);
    AdminClassDetailResponse getClass(long classSeq);
    AdminClassDetailResponse updateClass(long classSeq, AdminClassUpdateRequest request);
    void cancelClassMember(long classSeq, AdminClassCancelMemberRequest request);
    String getCancelMessageClassMember(long classSeq, long memberSeq);
    void finishClass(long classSeq);
    void attendanceClassMember(long classSeq, long memberSeq);
    void absentClassMember(long classSeq, long memberSeq);
}
