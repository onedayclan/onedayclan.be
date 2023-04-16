package com.clanone.onedayclan.clazz.application.service;

import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassCreateRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassDetailResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.response.LatestClassResponse;
import com.clanone.onedayclan.clazz.application.port.in.ClassPort;
import com.clanone.onedayclan.clazz.application.port.out.GetClassPort;
import com.clanone.onedayclan.clazz.application.port.out.ManageClassPort;
import com.clanone.onedayclan.common.application.port.out.ImagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        // ClassCategoryEntity category = getClassPort.getClassCategory(request.getCategorySeq())
        // ClassEntity classInfo = classManagePort.insertClass(ClassEntity.of());
        return null;
    }

    @Override
    public List<LatestClassResponse> getLatestClass() {
        return getClassPort.getFiveLatestClass().stream().map(LatestClassResponse::of).collect(Collectors.toList());
    }
}
