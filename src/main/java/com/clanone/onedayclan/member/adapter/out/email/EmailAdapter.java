package com.clanone.onedayclan.member.adapter.out.email;

import com.clanone.onedayclan.member.application.port.out.SendEmailPort;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailAdapter implements SendEmailPort {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String to, String subject, String body) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            // boolean multipart : 첨부파일 전송을 위한 multipart 여부
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false,"UTF-8");
            mimeMessageHelper.setTo(to); // 수신자
            mimeMessageHelper.setSubject(subject); // 제목
            mimeMessageHelper.setText(body,false); // 내용, html 여부
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("메일 발송 중 오류 발생. {}", e);
        }

    }
}
