package com.clanone.onedayclan.member.application.port.out;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface S3ClientPort {
    void uploadImage(MultipartFile image, String url);
}
