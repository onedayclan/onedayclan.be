package com.clanone.onedayclan.customer.adapter.out.persistence.repository;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.InquiryAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryAnswerRepository extends JpaRepository<InquiryAnswerEntity, Long> {
    List<InquiryAnswerEntity> findByInquirySeq(long seq);
    void deleteAllByInquirySeq(long inquirySeq);
}
