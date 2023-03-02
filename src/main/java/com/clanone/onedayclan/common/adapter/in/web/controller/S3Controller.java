package com.clanone.onedayclan.common.adapter.in.web.controller;

import com.clanone.onedayclan.OnedayclanResponse;
import com.clanone.onedayclan.common.adapter.in.web.response.ImageUploadResponse;
import com.clanone.onedayclan.common.application.port.in.AttachmentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class S3Controller {

    private final AttachmentPort attachmentPort;

    @PostMapping("/image/upload")
    public ResponseEntity<OnedayclanResponse<ImageUploadResponse>> uploadImage(MultipartFile image){
        return ResponseEntity.ok(OnedayclanResponse.of(attachmentPort.uploadImage(image)));
    }
}
