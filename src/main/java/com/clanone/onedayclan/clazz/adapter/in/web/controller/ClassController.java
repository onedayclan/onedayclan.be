package com.clanone.onedayclan.clazz.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.clazz.adapter.in.web.response.LatestClassResponse;
import com.clanone.onedayclan.clazz.application.port.in.ClassPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClassController {

    private final ClassPort classPort;
    @GetMapping("/main/class")
    public ResponseEntity<OnedayclanResponse<List<LatestClassResponse>>> getLatestClass(){
        return ResponseEntity.ok(OnedayclanResponse.of(classPort.getLatestClass()));
    }
}
