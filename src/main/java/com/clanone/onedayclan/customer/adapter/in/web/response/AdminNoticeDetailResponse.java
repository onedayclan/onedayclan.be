package com.clanone.onedayclan.customer.adapter.in.web.response;

import com.clanone.onedayclan.common.application.service.utils.ImageUtil;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.NoticeEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AdminNoticeDetailResponse {
    private long seq;
    private String title;
    private String content;
    private String imageUrl;
    private boolean showYn;

    public static AdminNoticeDetailResponse of(NoticeEntity notice) {
        return AdminNoticeDetailResponse.builder()
                .seq(notice.getSeq())
                .title(notice.getTitle())
                .content(notice.getContent())
                .imageUrl(notice.hasImage() ? ImageUtil.getS3Bucket() + notice.getImage().getUrl() : null)
                .showYn(notice.isShowYn())
                .build();
    }
}
