package com.clanone.onedayclan.clazz.adapter.out.persistence.repository;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassReviewRepository extends JpaRepository<ClassReviewEntity, Long>, ClassReviewCustomRepository {
    Page<ClassReviewEntity> findByClazzSeq(long classSeq, Pageable pageable);
}
