package com.clanone.onedayclan.clazz.adapter.out.persistence.repository;

import com.clanone.onedayclan.clazz.adapter.out.persistence.entity.ClassReviewQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassReviewQuestionRepository extends JpaRepository<ClassReviewQuestionEntity,Long> {
    List<ClassReviewQuestionEntity> findByUsedYnOrderByCreatedAtDesc(boolean usedYn);
}
