package com.clanone.onedayclan.common.application.port.out;

import com.clanone.onedayclan.common.adapter.out.persistence.entity.ImageEntity;

import java.util.List;

public interface ImagePort {
    Long saveImage(String url, String imageName);
    ImageEntity getImage(long imageSeq);
    void deleteImage(long imageSeq);
    List<ImageEntity> getImageListBySeq(List<Long> imageSeqList);
}
