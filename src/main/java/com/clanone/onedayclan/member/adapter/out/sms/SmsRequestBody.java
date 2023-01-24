package com.clanone.onedayclan.member.adapter.out.sms;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class SmsRequestBody {
    private String type; //SMS 타입 (SMS, LMS, MMS)
    private String contentType; //메시지 타입 (COMM : 일반메시지(default), AD : 광고메시지)
    private String countryCode; //국가 번호 (SENS 에서 제공하는 국가로의 발송만 가능)
    private String from; //발신번호 (사전 등록된 번호만 사용 가능)
    private String subject; //기본 메시지 제목
    private String content; //기본 메시지 내용
    private List<SmsMessage> messages; //메시지 정보
}
