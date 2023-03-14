package com.clanone.onedayclan.customer.application.port.out;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.NoticeEntity;

public interface ManageNoticePort {
    NoticeEntity saveNotice(NoticeEntity notice);
    void deleteNotice(long noticeSeq);
}
