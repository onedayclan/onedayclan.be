package com.clanone.onedayclan.customer.application.service;

import com.clanone.onedayclan.common.adapter.out.persistence.entity.ImageEntity;
import com.clanone.onedayclan.common.application.port.out.ImagePort;
import com.clanone.onedayclan.common.application.service.utils.ImageUtil;
import com.clanone.onedayclan.customer.adapter.in.web.request.NoticeCreateRequest;
import com.clanone.onedayclan.customer.adapter.in.web.request.NoticeSearchRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminNoticeDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminNoticeResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.NoticeDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.NoticeResponse;
import com.clanone.onedayclan.customer.adapter.out.model.NoticeSearchModel;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.NoticeEntity;
import com.clanone.onedayclan.customer.application.port.in.NoticePort;
import com.clanone.onedayclan.customer.application.port.out.GetNoticePort;
import com.clanone.onedayclan.customer.application.port.out.ManageNoticePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService implements NoticePort {

    private final GetNoticePort getNoticePort;
    private final ManageNoticePort manageNoticePort;
    private final ImagePort imagePort;

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

    @Override
    public Page<AdminNoticeResponse> getNoticeListForAdmin(NoticeSearchRequest request, Pageable pageable) {
        return getNoticePort.getNoticeListForAdmin(NoticeSearchModel.builder()
                        .title(request.getTitle())
                        .searchStartAt(request.getSearchStartAt())
                        .searchEndAt(request.getSearchEndAt())
                .build(), pageable);
    }

    @Override
    public AdminNoticeDetailResponse getNoticeForAdmin(long noticeSeq) {
        return AdminNoticeDetailResponse.of(getNoticePort.getNotice(noticeSeq));
    }

    @Override
    @Transactional
    public AdminNoticeDetailResponse insertNotice(NoticeCreateRequest request) {
        NoticeEntity notice = NoticeEntity.builder()
                                        .title(request.getTitle())
                                        .content(request.getContent())
                                        .showYn(request.isShowYn())
                                        .build();
        if(request.hasImage()) {
            this.connectNoticeImage(notice, request.getImageSeq());
        }

        return AdminNoticeDetailResponse.of(manageNoticePort.saveNotice(notice));
    }

    @Override
    @Transactional
    public void deleteNotice(long noticeSeq) {
        NoticeEntity notice = getNoticePort.getNotice(noticeSeq);
        if(notice.hasImage()) {
            imagePort.deleteImage(notice.getImage().getSeq());
        }
        manageNoticePort.deleteNotice(noticeSeq);
    }

    private void connectNoticeImage(NoticeEntity notice, long imageSeq) {
        ImageEntity image = imagePort.getImage(imageSeq);
        notice.connectImage(image);
        image.connectNotice();
    }
}
