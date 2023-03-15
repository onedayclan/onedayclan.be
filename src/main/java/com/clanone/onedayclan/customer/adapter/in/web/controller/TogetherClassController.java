package com.clanone.onedayclan.customer.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.common.resolver.LoginUserId;
import com.clanone.onedayclan.customer.adapter.in.web.request.ApplyTogetherClassRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.TogetherClassDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.TogetherClassResponse;
import com.clanone.onedayclan.customer.application.port.in.TogetherClassPort;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/together/class")
public class TogetherClassController {

    private final TogetherClassPort togetherClassPort;

    @PostMapping("")
    public ResponseEntity<OnedayclanResponse<Void>> applyTogetherClass(@Valid @RequestBody ApplyTogetherClassRequest request,
                                                                       @LoginUserId String userId){
        togetherClassPort.applyTogetherClass(userId, request);
        return ResponseEntity.ok(OnedayclanResponse.success());
    }

    @GetMapping("")
    public ResponseEntity<OnedayclanResponse<List<TogetherClassResponse>>> getTogetherClass(@LoginUserId String userId) {
        return ResponseEntity.ok(OnedayclanResponse.of(togetherClassPort.getTogetherClass(userId)));
    }

    @GetMapping("/{togetherClassSeq}")
    public ResponseEntity<OnedayclanResponse<TogetherClassDetailResponse>> getTogetherClassDetail(@LoginUserId String userId,
                                                                                                  @PathVariable long togetherClassSeq) {
        return ResponseEntity.ok(OnedayclanResponse.of(togetherClassPort.getTogetherClassDetail(userId, togetherClassSeq)));
    }
}
