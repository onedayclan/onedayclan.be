package com.clanone.onedayclan.clazz.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassCreateRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassDetailResponse;
import com.clanone.onedayclan.clazz.application.port.in.ClassPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/class")
@RequiredArgsConstructor
public class AdminClassController {

    private final ClassPort classPort;

    @PostMapping("")
    public ResponseEntity<OnedayclanResponse<AdminClassDetailResponse>> insertClass(@RequestBody AdminClassCreateRequest request) {
        return ResponseEntity.ok(OnedayclanResponse.of(classPort.insertClass(request)));
    }
}
