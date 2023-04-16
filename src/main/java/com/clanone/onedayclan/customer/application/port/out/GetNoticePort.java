package com.clanone.onedayclan.customer.application.port.out;

import com.clanone.onedayclan.customer.adapter.in.web.response.AdminNoticeResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.NoticeDetailResponse;
import com.clanone.onedayclan.customer.adapter.out.model.NoticeSearchModel;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.NoticeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GetNoticePort {
    List<NoticeEntity> getNoticeList();
    NoticeEntity getNotice(long seq);
    Page<AdminNoticeResponse> getNoticeListForAdmin(NoticeSearchModel model, Pageable pageable);
    NoticeEntity getNoticeForAdmin(long seq);

    List<NoticeEntity> getTop2NoticeList();
}
