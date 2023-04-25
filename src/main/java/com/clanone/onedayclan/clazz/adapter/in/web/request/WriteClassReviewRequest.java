package com.clanone.onedayclan.clazz.adapter.in.web.request;

import lombok.Getter;

import java.util.List;

@Getter
public class WriteClassReviewRequest {
    private int star;
    private List<Long> questions;
    private String content;
    private List<Long> images;
    private String wishClass;
}
