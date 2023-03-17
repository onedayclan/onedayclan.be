package com.clanone.onedayclan.customer.application.port.in;

import com.clanone.onedayclan.customer.adapter.in.web.request.NoticeCreateRequest;
import com.clanone.onedayclan.customer.adapter.in.web.request.NoticeSearchRequest;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminNoticeDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.AdminNoticeResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.NoticeDetailResponse;
import com.clanone.onedayclan.customer.adapter.in.web.response.NoticeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticePort {
    List<NoticeResponse> getNoticeList();
    NoticeDetailResponse getNotice(long seq);
    Page<AdminNoticeResponse> getNoticeListForAdmin(NoticeSearchRequest request, Pageable pageable);
    AdminNoticeDetailResponse getNoticeForAdmin(long seq);
    AdminNoticeDetailResponse insertNotice(NoticeCreateRequest request);
    void deleteNotice(long noticeSeq);
    AdminNoticeDetailResponse updateNotice(long noticeSeq, NoticeCreateRequest request);
}
