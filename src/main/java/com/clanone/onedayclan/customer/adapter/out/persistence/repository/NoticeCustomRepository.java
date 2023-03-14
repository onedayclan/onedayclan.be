package com.clanone.onedayclan.customer.adapter.out.persistence.repository;

import com.clanone.onedayclan.customer.adapter.in.web.response.AdminNoticeResponse;
import com.clanone.onedayclan.customer.adapter.out.model.NoticeSearchModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeCustomRepository {
    Page<AdminNoticeResponse> getNoticeListForAdmin(NoticeSearchModel model, Pageable pageable);
}
