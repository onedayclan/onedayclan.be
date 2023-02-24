package com.clanone.onedayclan.member.application.port.out;

public interface ImagePort {
    Long saveImage(String url, String imageName);
}
