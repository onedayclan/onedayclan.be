package com.clanone.onedayclan.customer.application.port.in;

import com.clanone.onedayclan.customer.adapter.in.web.request.ApplyTogetherClassRequest;
import com.clanone.onedayclan.customer.adapter.in.web.request.TogetherClassAnswerCreateRequest;
import com.clanone.onedayclan.customer.adapter.in.web.request.TogetherClassSearchRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTogetherClassDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminTogetherClassResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.TogetherClassDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.TogetherClassResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TogetherClassPort {
    void applyTogetherClass(String userId, ApplyTogetherClassRequest request);
    List<TogetherClassResponse> getTogetherClass(String userId);
    TogetherClassDetailResponse getTogetherClassDetail(String userId, long seq);
    Page<AdminTogetherClassResponse> getTogetherClassListForAdmin(TogetherClassSearchRequest request, Pageable pageable);
    AdminTogetherClassDetailResponse getTogetherClassForAdmin(long togetherClassSeq);
    void applyTogetherClassAnswer(long togetherClassSeq, TogetherClassAnswerCreateRequest request);
    void deleteTogetherClassAnswer(long togetherClassSeq);
}
