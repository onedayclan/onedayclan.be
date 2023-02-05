package com.clanone.onedayclan.member.adapter.in.web;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.member.application.port.in.AuthorizationNumberPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SmsController {
    private final AuthorizationNumberPort authorizationNumberPort;

    @PostMapping("/sms/send")
    public ResponseEntity<OnedayclanResponse<Void>> sendMessage(@Valid @RequestBody SmsRequest smsRequest) {
        authorizationNumberPort.sendAuthorizationNumber(smsRequest.getTo());
        return ResponseEntity.ok(OnedayclanResponse.success());
    }

    @PostMapping("/sms/confirm")
    public ResponseEntity<OnedayclanResponse<SmsConfirmResponse>> confirmSms(@Valid @RequestBody SmsConfirmRequest smsConfirmRequest) {
        return ResponseEntity.ok(OnedayclanResponse.of(authorizationNumberPort.confirmAuthorizationNumber(smsConfirmRequest)));
    }
}
