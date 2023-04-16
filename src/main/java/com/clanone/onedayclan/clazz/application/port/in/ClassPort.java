package com.clanone.onedayclan.clazz.application.port.in;

import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassCreateRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassDetailResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.response.LatestClassResponse;

import java.util.List;

public interface ClassPort {
    AdminClassDetailResponse insertClass(AdminClassCreateRequest request);
    List<LatestClassResponse> getLatestClass();
}
