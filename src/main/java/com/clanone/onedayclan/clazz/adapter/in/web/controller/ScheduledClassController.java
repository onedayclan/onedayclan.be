package com.clanone.onedayclan.clazz.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.response.ScheduledClassInfoResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.response.ScheduledClassResponse;
import com.clanone.onedayclan.clazz.application.port.in.ScheduledClassPort;
import com.clanone.onedayclan.common.resolver.LoginUserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scheduled")
public class ScheduledClassController {

    private final ScheduledClassPort scheduledClassPort;

    @GetMapping("/info")
    public ResponseEntity<OnedayclanResponse<ScheduledClassInfoResponse>> getScheduledClassInfo(@LoginUserId String userId){
        return ResponseEntity.ok(OnedayclanResponse.of(scheduledClassPort.getScheduledClassInfo(userId)));
    }

    @GetMapping("/class")
    public ResponseEntity<OnedayclanResponse<List<ScheduledClassResponse>>> getScheduledClass(@LoginUserId String userId){
        return ResponseEntity.ok(OnedayclanResponse.of(scheduledClassPort.getScheduledClassList(userId)));
    }

}
