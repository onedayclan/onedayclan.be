package com.clanone.onedayclan.customer.adapter.out.adapter;

import com.clanone.onedayclan.customer.adapter.in.web.response.AdminNoticeResponse;
import com.clanone.onedayclan.customer.adapter.out.model.NoticeSearchModel;
import com.clanone.onedayclan.customer.adapter.out.persistence.entity.NoticeEntity;
import com.clanone.onedayclan.customer.adapter.out.persistence.repository.NoticeRepository;
import com.clanone.onedayclan.customer.application.exception.NoticeNotFoundException;
import com.clanone.onedayclan.customer.application.port.out.GetNoticePort;
import com.clanone.onedayclan.customer.application.port.out.ManageNoticePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NoticeAdapter implements GetNoticePort, ManageNoticePort {

    private final NoticeRepository noticeRepository;

    @Override
    public List<NoticeEntity> getNoticeList() {
        return noticeRepository.findByShowYnOrderByCreatedAtDesc(true);
    }

    @Override
    public NoticeEntity getNotice(long seq) {
        return noticeRepository.findBySeqAndShowYn(seq,true).orElseThrow(() -> {throw new NoticeNotFoundException();});
    }

    @Override
    public Page<AdminNoticeResponse> getNoticeListForAdmin(NoticeSearchModel model, Pageable pageable) {
        return noticeRepository.getNoticeListForAdmin(model, pageable);
    }

    @Override
    public NoticeEntity getNoticeForAdmin(long seq) {
        return noticeRepository.findById(seq).orElseThrow(() -> {throw new NoticeNotFoundException();});
    }

    @Override
    public NoticeEntity saveNotice(NoticeEntity notice) {
        return noticeRepository.save(notice);
    }

    @Override
    public void deleteNotice(long noticeSeq) {
        noticeRepository.deleteById(noticeSeq);
    }
}
