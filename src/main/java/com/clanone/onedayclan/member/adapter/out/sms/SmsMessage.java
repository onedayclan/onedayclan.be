package com.clanone.onedayclan.member.adapter.out.sms;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SmsMessage {
    private String to; //수신번호
    private String subject; //개별 메시지 제목 (LMS, MMS 에서만 사용 가능)
    private String content; //개별 메시지 내용
}
