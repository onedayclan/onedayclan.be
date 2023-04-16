package com.clanone.onedayclan.clazz.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.OnedayclanResponse.PagingResult;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassCancelMemberRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassCreateRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassSearchRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.request.AdminClassUpdateRequest;
import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassCopyResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassDetailResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.response.AdminClassResponse;
import com.clanone.onedayclan.clazz.application.port.in.ClassPort;
import com.clanone.onedayclan.clazz.domain.enums.ClassStatus;
import com.clanone.onedayclan.member.domain.enums.MemberStatusType;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/class")
@RequiredArgsConstructor
public class AdminClassController {

    private final ClassPort classPort;

    @PostMapping("")
    public ResponseEntity<OnedayclanResponse<AdminClassDetailResponse>> insertClass(@RequestBody AdminClassCreateRequest request) {
        return ResponseEntity.ok(OnedayclanResponse.of(classPort.insertClass(request)));
    }

    @GetMapping("")
    public ResponseEntity<OnedayclanResponse<PagingResult<AdminClassResponse>>> getClassList(@RequestParam(required = false) String name,
                                                                                             @RequestParam(required = false) ClassStatus[] status,
                                                                                             @RequestParam(required = false) Boolean showYn,
                                                                                             @RequestParam(required = false) String createdStartAt,
                                                                                             @RequestParam(required = false) String createdEndAt,
                                                                                             @RequestParam(required = false) Long categorySeq,
                                                                                             @RequestParam(defaultValue = "1") int pageNo,
                                                                                             @RequestParam(defaultValue = "10") int pageSize) {
        Page<AdminClassResponse> result = classPort.searchClassList(AdminClassSearchRequest.builder()
                        .name(name)
                        .status(Objects.isNull(status) ? null : Arrays.stream(status).collect(Collectors.toList()))
                        .createdStartAt(Strings.isNotBlank(createdStartAt) ? LocalDateTime.parse(createdStartAt + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : null)
                        .createdEndAt(Strings.isNotBlank(createdEndAt) ? LocalDateTime.parse(createdEndAt + " 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : null)
                        .categorySeq(categorySeq)
                        .showYn(showYn)
                        .build(), PageRequest.of(pageNo-1, pageSize));
        return ResponseEntity.ok(OnedayclanResponse.of(result.getContent(), pageNo, result.getTotalElements()));
    }

    @GetMapping("/{classSeq}")
    public ResponseEntity<OnedayclanResponse<AdminClassDetailResponse>> getClass(@PathVariable long classSeq) {
        return ResponseEntity.ok(OnedayclanResponse.of(classPort.getClass(classSeq)));
    }

    @PutMapping("/{classSeq}")
    public ResponseEntity<OnedayclanResponse<AdminClassDetailResponse>> updateClass(@PathVariable long classSeq, @RequestBody AdminClassUpdateRequest request) {
        return ResponseEntity.ok(OnedayclanResponse.of(classPort.updateClass(classSeq, request)));
    }

    @GetMapping("/{classSeq}/copy")
    public ResponseEntity<OnedayclanResponse<AdminClassCopyResponse>> copyClass(@PathVariable long classSeq) {
        return ResponseEntity.ok(OnedayclanResponse.of(classPort.copyClass(classSeq)));
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
