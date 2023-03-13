package com.clanone.onedayclan.customer.application.port.out;

import com.clanone.onedayclan.customer.adapter.in.web.response.NoticeDetailResponse;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.NoticeEntity;

import java.util.List;

public interface GetNoticePort {
    List<NoticeEntity> getNoticeList();
    NoticeEntity getNotice(long seq);
}
