package com.clanone.onedayclan.member.application.port.out;

public interface SendSmsPort {
    void sendSMS(String content, String to);
}
