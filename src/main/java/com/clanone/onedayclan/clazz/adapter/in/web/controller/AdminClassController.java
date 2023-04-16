package com.clanone.onedayclan.clazz.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassCancelMemberRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassCreateRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassUpdateRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassDetailResponse;
import com.clanone.onedayclan.clazz.application.port.in.ClassPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/class")
@RequiredArgsConstructor
public class AdminClassController {

    private final ClassPort classPort;

    @PostMapping("")
    public ResponseEntity<OnedayclanResponse<AdminClassDetailResponse>> insertClass(@RequestBody AdminClassCreateRequest request) {
        return ResponseEntity.ok(OnedayclanResponse.of(classPort.insertClass(request)));
    }

    @GetMapping("/{classSeq}")
    public ResponseEntity<OnedayclanResponse<AdminClassDetailResponse>> getClass(@PathVariable long classSeq) {
        return ResponseEntity.ok(OnedayclanResponse.of(classPort.getClass(classSeq)));
    }

    @PutMapping("/{classSeq}")
    public ResponseEntity<OnedayclanResponse<AdminClassDetailResponse>> updateClass(@PathVariable long classSeq, @RequestBody AdminClassUpdateRequest request) {
        return ResponseEntity.ok(OnedayclanResponse.of(classPort.updateClass(classSeq, request)));
    }

    @PostMapping("/{classSeq}/cancel")
    public ResponseEntity<OnedayclanResponse<Void>> cancelClassMember(@PathVariable long classSeq, @RequestBody AdminClassCancelMemberRequest request) {
        classPort.cancelClassMember(classSeq, request);
        return ResponseEntity.ok(OnedayclanResponse.success());
    }

    @GetMapping("/{classSeq}/{memberSeq}/cancel")
    public ResponseEntity<OnedayclanResponse<String>> getCancelMessageClassMember(@PathVariable long classSeq, @PathVariable long memberSeq) {
        return ResponseEntity.ok(OnedayclanResponse.of(classPort.getCancelMessageClassMember(classSeq, memberSeq)));
    }

    @PostMapping("/{classSeq}/finish")
    public ResponseEntity<OnedayclanResponse<Void>> finishClass(@PathVariable long classSeq) {
        classPort.finishClass(classSeq);
        return ResponseEntity.ok(OnedayclanResponse.success());
    }

    @PostMapping("/{classSeq}/{memberSeq}/attendance")
    public ResponseEntity<OnedayclanResponse<Void>> attendanceClassMember(@PathVariable long classSeq, @PathVariable long memberSeq) {
        classPort.attendanceClassMember(classSeq, memberSeq);
        return ResponseEntity.ok(OnedayclanResponse.success());
    }

    @PostMapping("/{classSeq}/{memberSeq}/absent")
    public ResponseEntity<OnedayclanResponse<Void>> absentClassMember(@PathVariable long classSeq, @PathVariable long memberSeq) {
        classPort.absentClassMember(classSeq, memberSeq);
        return ResponseEntity.ok(OnedayclanResponse.success());
    }
}
