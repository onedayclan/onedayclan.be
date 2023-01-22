package com.clanone.onedayclan.member.adapter.in.web;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.member.application.port.in.SendAuthorizationNumberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SmsController {
    private final SendAuthorizationNumberPort sendAuthorizationNumberPort;

    @PostMapping("/sms/send")
    public ResponseEntity<OnedayclanResponse<Void>> sendMessage(@RequestBody SmsRequest smsRequest) {
        sendAuthorizationNumberPort.sendAuthorizationNumber(smsRequest.getTo());
        return ResponseEntity.ok(OnedayclanResponse.success());
    }
}
