package com.clanone.onedayclan.clazz.adapter.in.web.response;

import com.clanone.onedayclan.clazz.domain.enums.ClassStatus;
import com.clanone.onedayclan.common.application.service.utils.ImageUtil;
import lombok.Getter;

@Getter
public class ClassListResponse {
    private long seq;
    private String thumbnailUrl;
    private String name;
    private String startAt;
    private String description;
    private String category;
    private Integer organizationFee;
    private Integer normalFee;
    private ClassStatus status;

    public String getThumbnailUrl() {
        return ImageUtil.getS3Bucket() + this.thumbnailUrl;
    }

    public String getStatus(){
        return this.status.getName();
    }

    public ClassListResponse(long seq, String name, String startAt, String description, Integer organizationFee, Integer normalFee, ClassStatus status, String thumbnailUrl,String category) {
        this.seq = seq;
        this.name = name;
        this.startAt = startAt;
        this.description = description;
        this.organizationFee = organizationFee;
        this.normalFee = normalFee;
        this.status = status;
        this.thumbnailUrl = thumbnailUrl;
        this.category = category;
    }
}
