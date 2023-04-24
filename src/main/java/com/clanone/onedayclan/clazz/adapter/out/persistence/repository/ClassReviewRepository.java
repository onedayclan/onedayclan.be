package com.clanone.onedayclan.clazz.adapter.out.persistence.repository;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface ClassReviewRepository extends JpaRepository<ClassReviewEntity, Long> {
    long countByMemberSeqAndCreatedAtBetween(long memberSeq, LocalDateTime startAt, LocalDateTime endAt);
}
