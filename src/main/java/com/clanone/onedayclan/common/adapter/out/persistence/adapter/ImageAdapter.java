package com.clanone.onedayclan.common.adapter.out.persistence.adapter;

import com.clanone.onedayclan.common.adapter.out.persistence.entity.ImageEntity;
import com.clanone.onedayclan.common.adapter.out.persistence.repository.ImageEntityRepository;
import com.clanone.onedayclan.common.application.port.out.ImagePort;
import com.clanone.onedayclan.common.application.service.exception.ImageNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Override
    public ImageEntity getImage(long imageSeq) {
        return imageEntityRepository.findById(imageSeq).orElseThrow(() -> {throw new ImageNotFoundException();});
    }

    @Override
    public void deleteImage(long imageSeq) {
        imageEntityRepository.deleteById(imageSeq);
    }

    @Override
    public List<ImageEntity> getImageListBySeq(List<Long> imageSeqList) {
        return imageEntityRepository.findBySeqIn(imageSeqList);
    }
}
