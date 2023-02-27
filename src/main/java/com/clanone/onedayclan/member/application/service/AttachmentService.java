package com.clanone.onedayclan.member.application.service;

import com.clanone.onedayclan.member.adapter.in.web.ImageUploadResponse;
import com.clanone.onedayclan.member.application.port.in.AttachmentPort;
import com.clanone.onedayclan.member.application.port.out.ImagePort;
import com.clanone.onedayclan.member.application.port.out.S3ClientPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AttachmentService implements AttachmentPort {

    private final S3ClientPort s3ClientPort;
    private final ImagePort imagePort;

    @Override
    public ImageUploadResponse uploadImage(MultipartFile image){
        String fileName = image.getOriginalFilename();
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd/");
        String url = "image/" + now.format(formatter) + UUID.randomUUID().toString();

        s3ClientPort.uploadImage(image, url);
        Long savedImageId = imagePort.saveImage(url, fileName);

        return ImageUploadResponse.builder().id(savedImageId).build();
    }
}
