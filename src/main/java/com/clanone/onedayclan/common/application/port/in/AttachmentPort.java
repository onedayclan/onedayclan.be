package com.clanone.onedayclan.common.application.port.in;

import com.clanone.onedayclan.common.adapter.in.web.response.ImageUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentPort {
    ImageUploadResponse uploadImage(MultipartFile image);
}
