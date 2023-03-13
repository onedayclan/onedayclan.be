package com.clanone.onedayclan.member.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.common.resolver.LoginUserId;
import com.clanone.onedayclan.member.adapter.in.web.request.ChangePhoneRequest;
import com.clanone.onedayclan.member.adapter.in.web.request.PasswordChangeRequest;
import com.clanone.onedayclan.member.application.port.in.ManageMemberPort;
import com.clanone.onedayclan.member.application.port.in.PasswordPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final PasswordPort passwordPort;
    private final ManageMemberPort manageMemberPort;

    @PatchMapping("password/change")
    public ResponseEntity<OnedayclanResponse<Void>> changePassword(@LoginUserId String userId, @Valid @RequestBody PasswordChangeRequest passwordChangeRequest) {
        passwordPort.changePassword(passwordChangeRequest, userId);
        return ResponseEntity.ok(OnedayclanResponse.success());
    }

    @PatchMapping("mypage/change/phone")
    public ResponseEntity<OnedayclanResponse<Void>> changePhone(@LoginUserId String userId, @Valid @RequestBody ChangePhoneRequest changePhoneRequest) {
        manageMemberPort.changePhone(userId, changePhoneRequest);
        return ResponseEntity.ok(OnedayclanResponse.success());
    }
}