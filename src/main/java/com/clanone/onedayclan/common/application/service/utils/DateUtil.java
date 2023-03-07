package com.clanone.onedayclan.common.application.service.utils;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static LocalDateTime parseLocalDateTimeByYYYYMMDD(String str) {
        return LocalDateTime.parse(str + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static LocalDateTime[] parseDurationByYYYYMMDD(String start, String end) {
        return new LocalDateTime[]{LocalDateTime.parse(start + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), LocalDateTime.parse(start + " 23:59:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))};
    }
}
