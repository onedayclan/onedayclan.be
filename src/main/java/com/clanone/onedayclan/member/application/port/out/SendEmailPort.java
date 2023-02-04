package com.clanone.onedayclan.member.application.port.out;

public interface SendEmailPort {
    void sendEmail(String to, String subject, String body);
}
