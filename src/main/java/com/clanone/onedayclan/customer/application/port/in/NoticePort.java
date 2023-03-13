package com.clanone.onedayclan.customer.application.port.in;

import com.clanone.onedayclan.customer.adapter.in.web.response.NoticeDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.NoticeResponse;

import java.util.List;

public interface NoticePort {
    List<NoticeResponse> getNoticeList();
    NoticeDetailResponse getNotice(long seq);
}
