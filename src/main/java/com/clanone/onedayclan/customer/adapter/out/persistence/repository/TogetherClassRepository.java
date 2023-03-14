package com.clanone.onedayclan.customer.adapter.out.persistence.repository;

import com.clanone.onedayclan.customer.adapter.out.persistence.entity.TogetherClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TogetherClassRepository extends JpaRepository<TogetherClassEntity, Long> {
    List<TogetherClassEntity> findByMemberUserIdOrderByCreatedAtDesc(String Userid);
    Optional<TogetherClassEntity> findByMemberUserIdAndSeq(String userId, long seq);
}
