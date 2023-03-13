package com.clanone.onedayclan.customer.application.service;

import com.clanone.onedayclan.common.application.service.utils.ImageUtil;
import com.clanone.onedayclan.customer.adapter.in.web.response.NoticeDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.NoticeResponse;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.NoticeEntity;
import com.clanone.onedayclan.customer.application.port.in.NoticePort;
import com.clanone.onedayclan.customer.application.port.out.GetNoticePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService implements NoticePort {

    private final GetNoticePort getNoticePort;

    @Override
    public List<NoticeResponse> getNoticeList() {
        return getNoticePort.getNoticeList().stream().map(NoticeResponse::of).collect(Collectors.toList());
    }

    @Override
    public NoticeDetailResponse getNotice(long seq) {
        NoticeEntity notice = getNoticePort.getNotice(seq);

        return NoticeDetailResponse.builder()
                .title(notice.getTitle())
                .createdAt(notice.getCreatedAt())
                .imageUrl(ImageUtil.getS3Bucket() + notice.getImage().getUrl())
                .content(notice.getContent())
                .build();
    }
}
