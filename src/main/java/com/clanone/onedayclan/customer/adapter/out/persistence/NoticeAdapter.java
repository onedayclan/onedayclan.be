package com.clanone.onedayclan.customer.adapter.out.persistence;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.NoticeEntity;
import com.clanone.onedayclan.customer.adapter.out.persistence.repository.NoticeRepository;
import com.clanone.onedayclan.customer.application.exception.NoticeNotFoundException;
import com.clanone.onedayclan.customer.application.port.out.GetNoticePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NoticeAdapter implements GetNoticePort {

    private final NoticeRepository noticeRepository;

    @Override
    public List<NoticeEntity> getNoticeList() {
        return noticeRepository.findByShowYnOrderByCreatedAtDesc(true);
    }

    @Override
    public NoticeEntity getNotice(long seq) {
        return noticeRepository.findBySeqAndShowYn(seq,true).orElseThrow(() -> {throw new NoticeNotFoundException();});
    }
}
