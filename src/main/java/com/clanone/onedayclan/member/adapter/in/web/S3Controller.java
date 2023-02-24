package com.clanone.onedayclan.member.adapter.in.web;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.member.application.port.in.AttachmentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class S3Controller {

    private final AttachmentPort attachmentPort;

    @PostMapping("/image/upload")
    public ResponseEntity<OnedayclanResponse<ImageUploadResponse>> uploadImage(MultipartFile image){
        return ResponseEntity.ok(OnedayclanResponse.of(attachmentPort.uploadImage(image)));
    }
}
