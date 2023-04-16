package com.clanone.onedayclan.clazz.adapter.in.web.response;

import com.clanone.onedayclan.clazz.domain.enums.ClassStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AdminClassResponse {
    private long seq;
    private String name;
    private String category;
    private String thumbnailUrl;
    private int limitPeople;
    private long applicationPeople;
    private boolean showYn;
    private ClassStatus status;
    private LocalDateTime createdAt;
}
