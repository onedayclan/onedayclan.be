package com.clanone.onedayclan.customer.adapter.out.persistence.repository;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long>, NoticeCustomRepository{
    List<NoticeEntity> findByShowYnOrderByCreatedAtDesc(boolean showYn);
    Optional<NoticeEntity> findBySeqAndShowYn(long seq, boolean showYn);
    List<NoticeEntity> findTop2ByShowYnOrderByCreatedAtDesc(boolean showYn);
}
