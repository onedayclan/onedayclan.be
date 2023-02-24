package com.clanone.onedayclan.member.application.port.in;

import com.clanone.onedayclan.member.adapter.in.web.ImageUploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AttachmentPort {
    ImageUploadResponse uploadImage(MultipartFile image);
}
