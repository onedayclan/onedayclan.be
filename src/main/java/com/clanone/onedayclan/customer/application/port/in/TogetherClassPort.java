package com.clanone.onedayclan.customer.application.port.in;

import com.clanone.onedayclan.customer.adapter.in.web.request.ApplyTogetherClassRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.TogetherClassDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.TogetherClassResponse;

import java.util.List;

public interface TogetherClassPort {
    void applyTogetherClass(String userId, ApplyTogetherClassRequest request);
    List<TogetherClassResponse> getTogetherClass(String userId);
    TogetherClassDetailResponse getTogetherClassDetail(String userId, long seq);
}
