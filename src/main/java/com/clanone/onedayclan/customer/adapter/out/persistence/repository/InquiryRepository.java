package com.clanone.onedayclan.customer.adapter.out.persistence.repository;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.InquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InquiryRepository extends JpaRepository<InquiryEntity, Long> {
    List<InquiryEntity> findByMemberUserIdAndDeleteYn(String userId, boolean deleteYn);
    Optional<InquiryEntity> findBySeqAndDeleteYn(long seq, boolean deleteYn);
    Optional<InquiryEntity> findByMemberUserIdAndSeqAndDeleteYn(String userId, long seq, boolean deleteYn);
}
