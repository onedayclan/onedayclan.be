package com.clanone.onedayclan.member.application.port.out;

import reactor.core.publisher.Mono;

public interface SendSmsPort {
    String sendSMS(String content, String to);
}
