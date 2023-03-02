package com.clanone.onedayclan.common.adapter.out.persistence.adapter;

import com.clanone.onedayclan.common.adapter.out.persistence.entity.ImageEntity;
import com.clanone.onedayclan.common.adapter.out.persistence.repository.ImageEntityRepository;
import com.clanone.onedayclan.common.application.port.out.ImagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ImageAdapter implements ImagePort {
    private final ImageEntityRepository imageEntityRepository;

    @Override
    public Long saveImage(String url, String fileName) {
        ImageEntity image = ImageEntity.builder()
                .fileName(fileName)
                .url(url)
                .build();

        ImageEntity savedImage = imageEntityRepository.save(image);
        return savedImage.getSeq();
    }
}
